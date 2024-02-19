package com.atto.attoproject.config.exception;

import com.atto.attoproject.config.exception.error.ErrorResponseEntity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseControllerAdvice {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<?> handleBaseException(CustomException e){
        log.error("CustomException - error : ", e);
        return ErrorResponseEntity.toResponseEntity(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception){
        List<String> messages = new ArrayList<>();
        log.error("MethodArgumentNotValidException - error : ", exception);
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    String message = "[" +
                            fieldError.getDefaultMessage() +
                            "](은)는 필수 값입니다. 입력된 값: [" +
                            fieldError.getRejectedValue() +
                            "]";
                    messages.add(message);
                });

        CustomException customException = CustomException.of("401",String.join(",", messages),HttpStatus.UNAUTHORIZED);
        return ErrorResponseEntity.toResponseEntity(customException);
    }
}

package com.atto.attoproject.config.exception.error;

import com.atto.attoproject.config.exception.CustomException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
public class ErrorResponseEntity {
    private String code;
    private String message;
    private HttpStatus httpStatus;

    @Builder
    public ErrorResponseEntity(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static ResponseEntity<?> toResponseEntity(CustomException customException) {

        return ResponseEntity
                .status(customException.getHttpStatus())
                .body(
                        ErrorResponseEntity.builder()
                                .httpStatus(customException.getHttpStatus())
                                .message(customException.getMessage())
                                .code(customException.getCode())
                                .build()
                );
    }
}
package com.atto.attoproject.config.exception.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Getter
public class CustomException extends RuntimeException{
    protected String code;
    protected String message;
    protected HttpStatus httpStatus;

    public CustomException(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static CustomException of(String code, String message, HttpStatus httpStatus) {
        if (!StringUtils.hasText(code)) {
            code = "404";
        }
        if (!StringUtils.hasText(message)) {
            message = "찾을수 없습니다.";
        }
        if (ObjectUtils.isEmpty(httpStatus)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new CustomException(code, message, httpStatus);
    }
}

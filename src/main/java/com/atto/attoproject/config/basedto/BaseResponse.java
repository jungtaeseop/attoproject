package com.atto.attoproject.config.basedto;

import com.atto.attoproject.config.exception.error.CustomException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class BaseResponse<T> {
    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MESSAGE = "성공입니다.";

    protected String code;
    protected String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T data;

    private BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static BaseResponse successMessage() {
        return new BaseResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> BaseResponse<T> of(String code, String message, T data) {
        return new BaseResponse<>(code, message, data);
    }

    public static <T> ResponseEntity<?> toResponsefrom(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        BaseResponse.of(SUCCESS_CODE, SUCCESS_MESSAGE, data)
                );
    }

    public static ResponseEntity<?> toResponseEntity(CustomException customException) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        BaseResponse.of(customException.getCode(), customException.getMessage(), customException.getHttpStatus())
                );
    }
}

package com.atto.attoproject.config.basedto;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.data.response.JwtResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public static <T> BaseResponse<T> successMessage(String message) {
        return new BaseResponse<>(SUCCESS_CODE, message, null);
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

    /**
     * 인증 에러 Response
     * */
    public static void error(ServletResponse response, HttpStatus httpStatus, AuthenticationException exception) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(httpStatus.value());

        Map<String, Object> result = new HashMap<>();
        result.put("error", exception.getMessage());

        httpServletResponse.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(result)));
    }

    /**
     * 인증 성공
     * */
    public static void tokenSuccess(ServletResponse response, String token, JwtResponse jwtResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setHeader("Authorization", token);
        httpServletResponse.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(jwtResponse)));
    }
}

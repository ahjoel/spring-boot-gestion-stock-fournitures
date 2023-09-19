package com.gestock.fourniture.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private final String errorCode;
    private final HttpStatus httpStatus;

    public CustomException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

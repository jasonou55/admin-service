package com.jarvis.adminservice.exception;

import com.jarvis.adminservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;
    private HttpStatus statusCode;

    public ServiceException(ErrorCode errorCode, String message, HttpStatus statusCode) {
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

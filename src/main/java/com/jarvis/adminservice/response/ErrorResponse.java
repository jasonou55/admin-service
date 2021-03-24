package com.jarvis.adminservice.response;

import com.jarvis.adminservice.enums.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class ErrorResponse extends BaseResponse {

    private ErrorCode errorCode;
    private String message;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

package com.jarvis.adminservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarvis.adminservice.aspect.AuditAspect;
import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException np) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCode(ErrorCode.NULL_POINTER);
        errorResponse.setMessage(ErrorCode.NULL_POINTER.getMessage());

        LOGGER.error("Get NullPointerException error. {} ", (Object) np.getStackTrace());

        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException se) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCode(se.getErrorCode());
        errorResponse.setMessage(se.getMessage());

        LOGGER.error("Get ServiceException error. {} ", (Object) se.getStackTrace());

        return ResponseEntity.status(se.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCode(ErrorCode.INTERNAL_ERROR_PROCESSOR);
        errorResponse.setMessage(e.getMessage());

        LOGGER.error("Get Exception error. {} ", (Object) e.getStackTrace());

        return ResponseEntity.status(500).body(errorResponse);
    }
}

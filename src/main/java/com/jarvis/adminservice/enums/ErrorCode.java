package com.jarvis.adminservice.enums;

public enum ErrorCode {

    /**
     * Service Errors - error codes related to issues with the Service
     * Bucket 1
     */
    NOT_FOUND(10000, "Entity not found."),
    DUPLICATED_UNIQUE_FIELD(10001, "Unique field already exists"),

    /**
     * Auth Errors - errors codes related to the Authentication piece
     * Bucket 2
     */
    FORBIDDEN(20000, "You are not authorized to perform the request action."),
    AUTHENTICATION_FAILED(20001, "Authentication failed"),
    ACCESS_DENIED(20002, "Insufficient permissions, access denied"),
    UNAUTHORIZED(20003, "Reject unauthorized operation"),

    /**
     * Internal Errors - internal/unexpected errors
     * Bucket 3
     */
    INTERNAL_ERROR_PROCESSOR(30000, "An internal error occurred while processing."),
    NULL_POINTER(30001, "An null pointer error occurred."),
    GENERATE_IDENTIFIER_ERROR(30002, "Generate identifier failed."),
    ENCRYPT_ERROR(30003, "An error occurred while encrypting."),
    DECRYPT_ERROR(30004, "An error occurred while decrypting.");


    private final int code;
    private final String message;

    ErrorCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

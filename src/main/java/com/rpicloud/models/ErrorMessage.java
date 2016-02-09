package com.rpicloud.models;

import org.springframework.http.HttpStatus;

/**
 * Created by mixmox on 09/02/16.
 */
public class ErrorMessage {
    private String message;
    private String cause;
    private String stackTrace;

    private int errorCode;

    public ErrorMessage(String message, Exception exception, int errorCode) {
        this.message = message;
        this.cause = exception.getCause().toString();
        this.stackTrace = exception.getStackTrace().toString();
        this.errorCode = errorCode;
    }

    public ErrorMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

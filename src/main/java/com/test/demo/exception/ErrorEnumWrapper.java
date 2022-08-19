package com.test.demo.exception;

/**
 * @author zhan.zhao
 *
 */
public class ErrorEnumWrapper implements ApplicationThrowable {
    private String errorCode;
    private String message;
    private String type;

    ErrorEnumWrapper(String code, String message, String type) {
        this.errorCode = code;
        this.message = message;
        this.type = type;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }
}

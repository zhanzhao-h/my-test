package com.test.demo.exception;

/**
 * @author zhan.zhao
 *
 */
public enum SystemErrorEnum implements ApplicationThrowable {
    INTERNAL_ERROR("10001", "服务器内部错误"),
    UNDEFINED_ERROR("10002", "%s"),
    NETWORK_REQUEST_ERROR("10003", "网络繁忙，请稍后重试");

    private static final String TYPE = "SystemError";
    private ErrorEnumWrapper errorEnumWrapper;

    SystemErrorEnum(String code, String message) {
        this.errorEnumWrapper = of(code, message);
    }

    public static ErrorEnumWrapper of(String code, String message) {
        return new ErrorEnumWrapper(code, message, TYPE);
    }

    public String getErrorCode() {
        return this.errorEnumWrapper.getErrorCode();
    }

    public String getMessage() {
        return this.errorEnumWrapper.getMessage();
    }

    public String getType() {
        return this.errorEnumWrapper.getType();
    }

    public void throwException(final String errorId, final Object... args) {
        this.errorEnumWrapper.throwException(errorId, args);
    }

    public void throwException(final String errorId, final Throwable cause, final Object... args) {
        this.errorEnumWrapper.throwException(errorId, cause, args);
    }

    public ApplicationException wrapException(final String errorId, final Throwable cause, final Object... args) {
        return this.errorEnumWrapper.wrapException(errorId, cause, args);
    }
}

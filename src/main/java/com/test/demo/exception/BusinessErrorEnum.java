package com.test.demo.exception;

/**
 * @author zhan.zhao
 *
 */
public enum BusinessErrorEnum implements ApplicationThrowable {
    ILLEGAL_PARAMETER("10051", "参数错误"),
    DUPLICATE_KEY("10052", "违反业务唯一性限制"),
    FLOW_LIMIT("10053", "系统繁忙，请稍后重试"),
    TOKEN_INVALID("10054", "token无效"),
    ACCOUNT_EXIST("10055", "account已存在"),
    RESOURCE_NOT_FOUND("10056", "资源未找到");

    private static final String TYPE = "BusinessError";
    private ErrorEnumWrapper errorEnumWrapper;

    BusinessErrorEnum(String code, String message) {
        this.errorEnumWrapper = of(code, message);
    }

    public static ErrorEnumWrapper of(String code, String message) {
        return new ErrorEnumWrapper(code, message, TYPE);
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

    public String getErrorCode() {
        return this.errorEnumWrapper.getErrorCode();
    }

    public String getMessage() {
        return this.errorEnumWrapper.getMessage();
    }

    public String getType() {
        return this.errorEnumWrapper.getType();
    }
}

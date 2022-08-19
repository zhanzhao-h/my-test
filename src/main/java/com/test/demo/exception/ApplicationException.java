package com.test.demo.exception;


import com.test.demo.common.util.MessageUtil;

/**
 * @author zhan.zhao
 *
 */
public class ApplicationException extends RuntimeException implements ApplicationThrowable {
    private static final long serialVersionUID = 1630921681440137759L;
    private final String errorCode;
    private final String errorId;
    private final String type;
    private static final String MESSAGE_PREFIX = "error.";

    public ApplicationException(String errorId, ApplicationThrowable errorEnum) {
        this(errorId, errorEnum, null);
    }

    public ApplicationException(String errorId, ApplicationThrowable errorEnum, Throwable cause) {
        this(errorId, errorEnum, cause, (Object[]) null);
    }

    public ApplicationException(String errorId, ApplicationThrowable errorEnum,
                                Throwable cause, Object... args) {
        super(getI18nMessage(errorEnum, args), cause);
        this.errorId = errorId;
        this.errorCode = errorEnum.getErrorCode();
        this.type = this.getType(errorEnum);
    }

    public static String getI18nMessage(ApplicationThrowable errorEnum, Object... args) {
        String message = MessageUtil.getMessage(MESSAGE_PREFIX + errorEnum.getErrorCode(), new Object[0], errorEnum.getMessage());
        return String.format(message, args);
    }

    private String getType(ApplicationThrowable errorEnum) {
        return errorEnum != null ? errorEnum.getType() : "";
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorId() {
        return this.errorId;
    }

    public String getType() {
        return this.type;
    }
}

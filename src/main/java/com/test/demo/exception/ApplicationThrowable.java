package com.test.demo.exception;

/**
 * @author zhan.zhao
 *
 */
public interface ApplicationThrowable {
    default void throwException(String errorId, Object... args) {
        this.throwException(errorId, null, args);
    }

    default void throwException(String errorId, Throwable cause, Object... args) {
        throw this.wrapException(errorId, cause, args);
    }

    default ApplicationException wrapException(String errorId, Throwable cause, Object... args) {
        return new ApplicationException(errorId, this, cause, args);
    }

    String getMessage();

    String getErrorCode();

    String getType();
}

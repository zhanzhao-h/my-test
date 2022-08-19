package com.test.demo.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zhan.zhao
 *
 */
public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static RuntimeException unchecked(Exception e) {
        return e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e);
    }

    public static String getStackTraces(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @SafeVarargs
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause))
                    return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}

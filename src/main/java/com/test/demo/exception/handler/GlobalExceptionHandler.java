package com.test.demo.exception.handler;

import com.test.demo.common.util.ExceptionUtil;
import com.test.demo.common.util.HeaderUtil;
import com.test.demo.common.util.MessageUtil;
import com.test.demo.exception.ApplicationException;
import com.test.demo.exception.BusinessErrorEnum;
import com.test.demo.exception.SystemErrorEnum;
import com.test.demo.model.ResponseContainer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.NestedServletException;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author zhan.zhao
 *
 */
@ControllerAdvice({"com.test.demo"})
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public static final String FEIGN = "feign.";

    private String getMessage(String key, Object[] args, String defaultValue) {
        return MessageUtil.getMessage(key, args, defaultValue);
    }

    private String getMessage(Exception e, Object[] args) {
        return this.getMessage(e.getClass(), args);
    }

    private String getMessage(Class<? extends Exception> clz, Object[] args) {
        String name = "GlobalExceptionHandler." + clz.getSimpleName();
        return this.getMessage(name, args, "HTTP请求参数不合法");
    }

    @ExceptionHandler({ Throwable.class })
    public ResponseContainer<Void> handleException(Throwable ex) {
        if (StringUtils.startsWith(ex.getClass().getName(), FEIGN)) {
            return this.handleApplicationException(SystemErrorEnum.NETWORK_REQUEST_ERROR.wrapException("4yiyaWuRQVPMcVgSo1HiAB", ex));
        }
        return this.handleApplicationException(SystemErrorEnum.INTERNAL_ERROR.wrapException("QXF5Mk3D5x7A7Dj98cR84m", ex));
    }

    @ExceptionHandler({ DuplicateKeyException.class })
    public ResponseContainer<Void> handleDuplicateKeyException(DuplicateKeyException ex) {
        return this.handleApplicationException(BusinessErrorEnum.DUPLICATE_KEY.wrapException("5dscWd99k5kSZj52Lzyg6p", ex));
    }

    @ExceptionHandler({ ApplicationException.class })
    public ResponseContainer<Void> handleApplicationException(ApplicationException ex) {
        GlobalExceptionHandler.log.error("transactionId:{}, errCode:{}, type:{}, errorId:{}, message:{}. cause:{}",
                HeaderUtil.getTransactionId(), ex.getErrorCode(), ex.getType(), ex.getErrorId(), ex.getMessage(),
                ExceptionUtil.getStackTraces(ex));
        return ResponseContainer.error(ex);
    }

    @ExceptionHandler({ NestedServletException.class })
    public ResponseContainer<Void> handleNestedServletException(NestedServletException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof ApplicationException) {
            return this.handleApplicationException((ApplicationException)cause);
        }
        if (cause instanceof DuplicateKeyException) {
            return this.handleDuplicateKeyException((DuplicateKeyException)cause);
        }
        return this.handleException(cause);
    }

    @ExceptionHandler({ TypeMismatchException.class })
    @Nonnull
    public ResponseContainer<Void> typeMismatchHandler(TypeMismatchException exception) {
        String detailMsg = this.getMessage(TypeMismatchException.class,
                new String[] { Objects.toString(exception.getValue()), Objects.toString(exception.getRequiredType()) });
        if (exception instanceof MethodArgumentTypeMismatchException) {
            detailMsg = this.getMessage(exception, new String[] { detailMsg });
        }
        return this.handleApplicationException(BusinessErrorEnum.ILLEGAL_PARAMETER.wrapException("WGYrdBvigGTLCenSNGvnTV", exception, detailMsg));
    }

    @ExceptionHandler({ BindException.class })
    public ResponseContainer<Void> bindExceptionHandler(BindException exception) {
        return this.buildValidResult(exception.getBindingResult(), exception);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseContainer<Void> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        return this.buildValidResult(exception.getBindingResult(), exception);
    }

    private ResponseContainer<Void> buildValidResult(BindingResult bindingResult, Exception e) {
        StringBuilder detailMsg = new StringBuilder();
        for (int i = 0; i < bindingResult.getFieldErrors().size(); ++i) {
            if (i > 0) {
                detailMsg.append(";");
            }
            FieldError error = bindingResult.getFieldErrors().get(i);
            String defaultMessage = error.getDefaultMessage();
            String msg = this.getMessage(defaultMessage, new Object[0], defaultMessage);
            detailMsg.append(msg);
        }
        String errorMessage = detailMsg.toString();
        return this.handleApplicationException(BusinessErrorEnum.ILLEGAL_PARAMETER.wrapException("5SxFgVgQG5mVPPEuCxWF63", e, errorMessage));
    }

    @ExceptionHandler({ HttpMessageConversionException.class })
    public ResponseContainer<Void> httpMessageConversionHandler(HttpMessageConversionException exception) {
        String detailMsg = this.getMessage(HttpMessageConversionException.class, new String[0]);
        return this.handleApplicationException(BusinessErrorEnum.ILLEGAL_PARAMETER.wrapException("Xe79MrpWEu47dD288YWX7Y", exception, detailMsg));
    }

    @ExceptionHandler({ ServletRequestBindingException.class })
    public ResponseContainer<Void> httpServletRequestBindingException(ServletRequestBindingException exception) {
        String detailMsg = this.getMessage(ServletRequestBindingException.class, new String[0]);
        return this.handleApplicationException(BusinessErrorEnum.ILLEGAL_PARAMETER.wrapException("Xe79MrpWEu47dD288YWX7Y", exception, detailMsg));
    }

}

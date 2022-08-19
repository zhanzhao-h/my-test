package com.test.demo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author zhan.zhao
 *
 */
public class MessageUtil {
    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);
    private static MessageSource messageSource = ApplicationContextHolder.getBean(MessageSource.class);

    private MessageUtil() {
    }

    public static String getMessage(String key, Object[] args, String defaultValue) {
        if (messageSource != null) {
            try {
                return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
            } catch (NoSuchMessageException var4) {
                log.trace("Key {} not found.", key);
            }
        }
        return defaultValue;
    }
}

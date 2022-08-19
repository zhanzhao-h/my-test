package com.test.demo.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author zhan.zhao
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static synchronized void setContext(ApplicationContext context) {
        applicationContext = context;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        setContext(applicationContext);
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(name, clazz);
    }
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        Map<String,T> beanMaps = applicationContext.getBeansOfType(clazz);
        return !beanMaps.isEmpty() ? beanMaps.values().iterator().next() : null;
    }
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入");
        }
    }

}

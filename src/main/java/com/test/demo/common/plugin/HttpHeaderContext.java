package com.test.demo.common.plugin;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhan.zhao
 */
public class HttpHeaderContext {

    private HttpHeaderContext() {
    }

    private static final TransmittableThreadLocal<Map<String, String>> PASS_THRU_HEADERS = new TransmittableThreadLocal<Map<String, String>>() {
        protected Map<String, String> initialValue() {
            return new HashMap<>(16);
        }
    };
    public static Map<String, String> getPassThruHeaderMap() {
        return PASS_THRU_HEADERS.get();
    }

    public static void clear() {
        PASS_THRU_HEADERS.remove();
    }
}

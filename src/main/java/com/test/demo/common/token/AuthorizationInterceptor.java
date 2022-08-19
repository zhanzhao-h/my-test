package com.test.demo.common.token;

import com.test.demo.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    /**
     *  拦截器 验证 Authorization
     * @author zhan.zhao
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Token annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Token.class);
        } else {
            return true;
        }
        if (annotation == null || !annotation.validate()) {
            return true;
        }
        //从header中获取token
        String token = request.getHeader(Constants.AUTHORIZATION);
        //存在时，此处只判断是否等于  123456
        if (!"123456".equals(token)) {
            log.info("缺少token，拒绝访问");
            //重定向至filter错误处理
            responseError(response,300,"dsad");
            return false;
        }
        //token校验通过 后续逻辑
        return true;
    }
    private void responseError(ServletResponse response, int code, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/api/v1/filter/token_error");
        } catch (IOException error) {
            log.error(error.getMessage());
        }
    }

}

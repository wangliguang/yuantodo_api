package com.guang.yuantodo.utils.interceptor;

import com.guang.yuantodo.utils.JwtToken;
import com.guang.yuantodo.utils.aop.AuthToken;
import net.bytebuddy.utility.JavaConstant;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class SourceAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 类上是否打注解
        boolean clazzAnnotationPresent = handlerMethod.getBeanType().isAnnotationPresent(AuthToken.class);
        //判断方法上是否有打该注解
        boolean methodsAnnotationPresent = handlerMethod.getMethod().isAnnotationPresent(AuthToken.class);

        AuthToken clazzAnnotation = handlerMethod.getBeanType().getAnnotation(AuthToken.class);
        AuthToken methodAnnotation = handlerMethod.getMethodAnnotation(AuthToken.class);

        if (methodsAnnotationPresent && methodAnnotation.auth()) {
            JwtToken.validateToken(request.getHeader("Authorization"));
            return true;
        }

        if (!methodsAnnotationPresent && clazzAnnotationPresent && clazzAnnotation.auth()) {
            JwtToken.validateToken(request.getHeader("Authorization"));
            return true;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

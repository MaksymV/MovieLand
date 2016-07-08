package com.volomak.movieland.controller.interceptor;

import com.volomak.movieland.controller.exception.RestrictAccessException;
import com.volomak.movieland.service.cache.UserTokenCache;
import com.volomak.movieland.util.PermittedRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

@Component
public class MovielandInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserTokenCache userTokenCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Check user roles");

        MDC.put("requestId", UUID.randomUUID().toString());
        String authToken = request.getHeader("authToken");
        String userLogin = null;
        if (request.getUserPrincipal() != null){
            userLogin = request.getUserPrincipal().getName();
        }

        final Method method = ((HandlerMethod) handler).getMethod();

        if (method.isAnnotationPresent(PermittedRoles.class)) {
            String[] allowedRoles = method.getAnnotation(PermittedRoles.class).roles();
            log.info("allowedRoles = {} - token: {}", Arrays.asList(allowedRoles), authToken);
            if (authToken == null || !(userTokenCache.validateRole(UUID.fromString(authToken), allowedRoles))) {
                throw new RestrictAccessException("Access denied. Wrong credentials");
            }
        }

        if (userLogin == null) {
            MDC.put("userLogin", "guest");
        } else {
            MDC.put("userLogin", userLogin);
        }
        return super.preHandle(request, response, handler);
    }
}

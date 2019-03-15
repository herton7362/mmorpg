package com.kratos.game.gangster.aop;

import com.alibaba.fastjson.JSON;
import com.kratos.game.gangster.common.CommonConstant;
import com.kratos.game.gangster.player.PlayerContext;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private PlayerService playerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();

        if (clazz.isAnnotationPresent(PrePermissions.class)) {
            PrePermissions clazzPermissions = clazz.getAnnotation(PrePermissions.class);
            if (clazzPermissions != null && clazzPermissions.required()) {
                if (method.isAnnotationPresent(PrePermissions.class)) {
                    PrePermissions prePermissions = method.getAnnotation(PrePermissions.class);
                    if (prePermissions != null && prePermissions.required()) {
                        return hasLogin(request, response);
                    }
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

    private boolean hasLogin(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        Player player = playerService.findByToken(token);
        if(player == null) {
            Map<String, Object> responseWithMap = new HashMap<>();
            responseWithMap.put("message", "您未登录，请先登录后再进行操作！");
            responseWithMap.put("status", HttpStatus.UNAUTHORIZED.value());
            responseWithMap.put("timestamp", new Date().getTime());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CommonConstant.CONTENT_TYPE);
            response.setCharacterEncoding(CommonConstant.UTF8);
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                outputStream.write(JSON.toJSONString(responseWithMap).getBytes(CommonConstant.UTF8));
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to response");
            }
            return false;
        }
        PlayerContext.setPlayer(player);
        return true;
    }
}

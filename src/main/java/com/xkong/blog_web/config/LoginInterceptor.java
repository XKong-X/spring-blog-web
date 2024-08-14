package com.xkong.blog_web.config;

import com.xkong.blog_web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-30
 * Time: 22:40
 * Version:
 */
@Slf4j
@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验 token
//        System.out.println("测试");
//        System.out.println(request.getHeader("user_token"));

        // 检查请求的 URL 是否为图标请求
        String requestURI = request.getRequestURI();
        log.info("前端访问的路径:"+requestURI);
//        if (requestURI == "/error" || requestURI.endsWith(".ico")
//                || requestURI.endsWith(".png")
//                || requestURI.endsWith(".jpg")
//                || requestURI.endsWith(".jpeg")
//                || requestURI.endsWith(".gif")) {
//            return true; // 直接通过，不进行 token 校验
//        }

        String token = request.getHeader("user_token");
//        System.out.println(token);
        log.info("获取 token, token:" + token);
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            response.setStatus(401);// 未登录
            log.info("token 不合法, 可能是用户未登录");
            return false;
        }
        return true;
    }
}

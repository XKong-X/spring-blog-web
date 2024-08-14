package com.xkong.blog_web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-30
 * Time: 23:19
 * Version:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final List<String> EXCLUDE_PATH = Arrays.asList(
            "/user/login",
            "/**/*.html",
            "/blog-editormd/**",
            "/css/**",
            "/js/**",
            "/pic/**"
    );
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")// 全部拦截
                .excludePathPatterns(EXCLUDE_PATH);
    }
}

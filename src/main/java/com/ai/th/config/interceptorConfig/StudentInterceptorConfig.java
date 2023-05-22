package com.ai.th.config.interceptorConfig;

import com.ai.th.config.interceptor.StudentJwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StudentInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/student/login",
                        "/student/register",
                        "/file/**",
                        "/**/export",
                        "/alipay/**"
                        );//拦截所有请求，通过判断token是否合法来决定是否登录
    }
    @Bean
    public StudentJwtInterceptor jwtInterceptor(){
        return new StudentJwtInterceptor();
    }
}

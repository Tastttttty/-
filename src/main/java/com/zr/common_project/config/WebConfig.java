package com.zr.common_project.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zr.common_project.custominterceptor.TokenInteceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public TokenInteceptor jwtInterceptor() {
		return new TokenInteceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor())
				// 拦截所有请求，通过判断是否有 @JwtToken 注解 决定是否需要登录
				.addPathPatterns("/**");
	}



}

package com.ssafy.enjoytrip.config;

import com.ssafy.enjoytrip.util.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	// mvc 설정
	// 인터셉터 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO 인터셉터 추가 메서드
//		registry.addInterceptor(new LoginInterceptor())
//				.addPathPatterns("/notice/write", "/user/**")
//				.excludePathPatterns("/user/login", "/user/join", "/user/check/*");
	}

	// 뷰 컨트롤 설정
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO 뷰 컨트롤 추가 메서드
		registry.addViewController("/").setViewName("index");
	}
}

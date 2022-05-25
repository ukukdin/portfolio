package com.project2.movieproject.config;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//스프링부트에서는 설정파일을 자바 파일로 사용합니다.
@Configuration	//스프링 설정파일임을 의미
@PropertySource("classpath:/application.properties")
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private org.springframework.context.ApplicationContext applicationContext;
	
	//@Value : application.properties 값을 직접 참조
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${server.port}")
	private String port;
	
	@Bean
	public void test() {
		System.out.println("테스트 빈 생성!");
		System.out.println("application.properties 안에 값:" + url);
		System.out.println("application.properties 안에 값:" + port);
		
		int count = applicationContext.getBeanDefinitionCount();
		System.out.println("IOC컨테이너 안에 있는 객체의 수:" + count);
	}
}

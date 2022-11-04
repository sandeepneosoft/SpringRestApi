package com.example.app.filter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.filter.RequestResponseLoggers;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<RequestResponseLoggers> createLoggers(@Autowired RequestResponseLoggers filterLogger) {
		FilterRegistrationBean<RequestResponseLoggers> bean = new FilterRegistrationBean<RequestResponseLoggers>();
		bean.setFilter(filterLogger);
		bean.addUrlPatterns("/edit-user/*");
		bean.setOrder(1);
		return bean;
	}
}

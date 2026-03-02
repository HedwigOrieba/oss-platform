package com.bazotech.store.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	/* For my view controllers */
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("some_path").setViewName("some_target_viewName");
	}
	
}

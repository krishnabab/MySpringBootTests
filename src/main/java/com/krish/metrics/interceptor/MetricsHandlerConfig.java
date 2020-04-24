package com.krish.metrics.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ConditionalOnProperty(
	    value="quantum.metrics.enabled", 
	    havingValue = "true", 
	    matchIfMissing = true)
public class MetricsHandlerConfig extends WebMvcConfigurerAdapter {
	   @Autowired
	   MetricsHandler metricsHandler;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(metricsHandler);
	   }
	   
	}
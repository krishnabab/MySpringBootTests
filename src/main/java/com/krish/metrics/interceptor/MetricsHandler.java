package com.krish.metrics.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.krish.quantum.monitoring.QuantumData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MetricsHandler implements HandlerInterceptor {
		
		@Autowired
		QuantumData quantumData;
	  	
	   @Override
	   public boolean preHandle
	      (HttpServletRequest request, HttpServletResponse response, Object handler) 
	      throws Exception {
	      
	      log.info(">>>Pre Handle method is Calling");
	      long receivedTimestamp = System.currentTimeMillis();
			log.info("Request URL::" + request.getRequestURL().toString()
					+ ":: Start Time=" + System.currentTimeMillis());
			request.setAttribute("receivedTimestamp", receivedTimestamp);
			quantumData.setReceivedTimestamp(receivedTimestamp);
	      return true;
	   }
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, ModelAndView modelAndView) throws Exception {
	      
	      log.info(">>>>Post Handle method is Calling");
	      long finishedCreatedTimestamp = System.currentTimeMillis();
	      long receivedTimestamp = (Long) request.getAttribute("receivedTimestamp");
			log.info("Request URL::" + request.getRequestURL().toString()
					+ ":: End Time=" + System.currentTimeMillis());
			request.setAttribute("finishedCreatedTimestamp", finishedCreatedTimestamp);
			quantumData.setFinishedCreatedTimestamp(finishedCreatedTimestamp);
			request.setAttribute("processingTime", finishedCreatedTimestamp-receivedTimestamp);
			
	   }
	   @Override
	   public void afterCompletion
	      (HttpServletRequest request, HttpServletResponse response, Object 
	      handler, Exception exception) throws Exception {
	      
	      log.info(">>>>Request and Response is completed");
	   }
	}
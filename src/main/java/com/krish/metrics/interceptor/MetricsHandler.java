package com.krish.metrics.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.krish.quantum.monitoring.QuantumData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MetricsHandler implements HandlerInterceptor {
		
		//@Autowired
		public QuantumData quantumData;
		
		@Autowired
		QuantumClientService quantumClient;
	  	
	   @Override
	   public boolean preHandle
	      (HttpServletRequest request, HttpServletResponse response, Object handler) 
	      throws Exception {
	      
	      log.info(">>>Pre Handle method is Calling");
	      long receivedTimestamp = System.currentTimeMillis();
			log.info("Request URL::" + request.getRequestURL().toString()
					+ ":: Start Time=" + System.currentTimeMillis());
			request.setAttribute("receivedTimestamp", receivedTimestamp);
			if(quantumData != null) {
				log.info("quantumData"+quantumData.toString());
				this.quantumData.setReceivedTimestamp(receivedTimestamp);
			}
			else {
				log.info("This is badly null");
			}
			
			if(quantumData != null)
			log.info("quantum client "+ quantumClient);
			
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
			log.info("in Controller"+quantumData.toString());
			quantumClient.postAddEquipementMetricsToQuantum(quantumData);
	   }
	   @Override
	   public void afterCompletion
	      (HttpServletRequest request, HttpServletResponse response, Object 
	      handler, Exception exception) throws Exception {
	      
	      log.info(">>>>Request and Response is completed");
	   }
	}
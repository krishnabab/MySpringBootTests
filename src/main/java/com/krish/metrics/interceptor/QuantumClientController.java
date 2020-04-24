package com.krish.metrics.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.krish.quantum.monitoring.QuantumData;

import lombok.extern.slf4j.Slf4j;

//@Controller
@Slf4j
public class QuantumClientController {
	
	@Autowired
	QuantumClient quantumClient;
	
	@Autowired
	QuantumData quantumData;
	
	public void postAddEquipmentMetrics() {
		log.info("in Controller"+quantumData.toString());
		//quantumClient.postAddEquipementMetricsToQuantum(quantumData);
		
	}
	
}

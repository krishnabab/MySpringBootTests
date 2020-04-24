package com.krish.metrics.interceptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.krish.quantum.monitoring.QuantumData;

import lombok.extern.slf4j.Slf4j;

//import com.charter.techmobile.common.feign.FeignGenericConfig;
//import com.charter.techmobile.techbuffer.exception.CustomErrorDecoder;

@FeignClient(value = "tm-techbuffer-quantumclient", url = "${quantum.kafa.url}")
//,configuration = { CustomErrorDecoder.class, FeignGenericConfig.class }))
public interface QuantumClientService {
	
	@PostMapping(value = "/qube?bulk=false", produces = MediaType.APPLICATION_JSON_VALUE)
	void postAddEquipementMetricsToQuantum(@RequestBody QuantumData quantumData);
}

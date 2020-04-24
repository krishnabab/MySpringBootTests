package com.krish.metrics.interceptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.krish.quantum.monitoring.QuantumData;


//import com.charter.techmobile.common.feign.FeignGenericConfig;
//import com.charter.techmobile.techbuffer.exception.CustomErrorDecoder;

@FeignClient(value = "tm-techbuffer-quantumclient", url = "${quantum.kafa.url}")
//,configuration = { FeignGenericConfig.class })
@Component
public interface QuantumClient {
	
	@PostMapping(value = "/qube?bulk=false", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity postAddEquipementMetricsToQuantum(@RequestHeader("Host") String host,
			@RequestHeader("user-agent") String userAgent,
			@RequestHeader("adrum_1") String adrum_1,
			@RequestHeader("adrum") String adrum,
			@RequestBody QuantumData quantumData);
}

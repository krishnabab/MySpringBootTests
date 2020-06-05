* To get dispatcher servlet request data with in other places of springboot code
	ResettableStreamHttpServletRequest servletReq = (ResettableStreamHttpServletRequest)(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest());

* Spring boot property to allow Bean overriding 
	spring.main.allow-bean-definition-overriding: true
	
* Config loading based on property
	@Configuration
	@ConditionalOnProperty(
	    value="quantum.metrics.externalcall.monitoring.enabled", 
	    havingValue = "true",
	    matchIfMissing = false )
	@ComponentScan("com.xyz.techmobile.common.abc.*")
* After spring appln started to do for example enrich after RestTemplate bean created
	@PostConstruct
	public void enrichRestCleint() {
		restTemplate.getInterceptors().add(restTemplateQuantumInterceptor);
	}
* When override a bean but want to load only if no bean was found with that name
	@ConditionalOnMissingBean(RestTemplate.class)		
* Get the working restendpoint method name from httpservlet request 
	HttpServletRequest 
* Use properties from POM.xml
	@Autowired
	BuildProperties buildProperties;
		buildProperties.getName()
		buildProperties.getVersion()
* Springboot debugging 
	Pass these vmargument
	-Dlogging.level.org.springframework=debug
	Program arguments
	--debug
	
* Junit 4 mockito example 
	package com.charter.techmobile.common.quantum.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.info.BuildProperties;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.charter.techmobile.common.quantum.httpwrapper.ResettableStreamHttpServletRequest;
import com.charter.techmobile.common.quantum.httpwrapper.ResettableStreamHttpServletResponse;
import com.charter.techmobile.common.quantum.model.Message;
import com.charter.techmobile.common.quantum.model.QuantumData;
import com.charter.techmobile.common.util.Constants;


@RunWith(MockitoJUnitRunner.class)
public class QuantumDataBuilderTest {
	
	@Mock
	BuildProperties buildProperties;
	
	@Mock
	Authentication auth;
	
	@InjectMocks
	QuantumDataBuilder quantumDataBuilder = new QuantumDataBuilder();
	
	ResettableStreamHttpServletRequest request;
	ResettableStreamHttpServletResponse response;
	Message msg;
	
	
	@Before
	public void intializer() throws IOException {
		HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
		request = new ResettableStreamHttpServletRequest(httpRequest);
		HttpServletResponse httpResponse = Mockito.mock(HttpServletResponse.class);
		response = new ResettableStreamHttpServletResponse(httpResponse);
		msg = Mockito.mock(Message.class);
		when(msg.getName()).thenReturn(Constants.MSG_IN_RES);
		request.requestId = "12345678ABCD";
		when(httpRequest.getHeaderNames()).thenReturn(Collections.enumeration(Collections.singleton("one")));
		when(httpRequest.getInputStream()).thenReturn(new DelegatingServletInputStream(IOUtils.toInputStream("Testinput", Charset.defaultCharset())));
		when(auth.getName()).thenReturn("TEST USER");
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	public void testBuildQuantumDataResettableStreamHttpServletRequest() throws IOException, JSONException {
		QuantumData quantumData = quantumDataBuilder.buildQuantumData(request, response, msg);
		assertEquals("12345678ABCD"+Constants.MSG_IN_RES, quantumData.getTraceId()+quantumData.getMessage().getName());
	}

}

* junit 5 mockito example 

	package com.charter.techmobile.common.quantum.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.charter.techmobile.common.quantum.httpwrapper.ResettableStreamHttpServletRequest;
import com.charter.techmobile.common.quantum.httpwrapper.ResettableStreamHttpServletResponse;
import com.charter.techmobile.common.quantum.model.Message;
import com.charter.techmobile.common.quantum.model.QuantumData;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
class QuantumServiceImplTest {
	
	@Mock
	QuantumDataBuilder quantumDataBuilder;

	@Mock
	QuantumRestClient quantumClient;
	
	@InjectMocks
	QuantumServiceImpl quantumService = new QuantumServiceImpl();
	
	ResettableStreamHttpServletRequest request;
	ResettableStreamHttpServletResponse response;
	Message msg;
	QuantumData quantumData;
	
	@BeforeEach
	public void intializer() throws IOException, JSONException {
		HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
		request = new ResettableStreamHttpServletRequest(httpRequest);
		HttpServletResponse httpResponse = Mockito.mock(HttpServletResponse.class);
		response = new ResettableStreamHttpServletResponse(httpResponse);
		msg = Mockito.mock(Message.class);
		//when(msg.getName()).thenReturn(Constants.MSG_IN_RES);
		request.requestId = "12345678ABCD";
		quantumData = Mockito.mock(QuantumData.class);
		quantumData.setMessage(msg);
		when(quantumDataBuilder.buildQuantumData(request, response, msg)).thenReturn(quantumData);
		ResponseEntity res = Mockito.mock(ResponseEntity.class);
		when(res.getStatusCode()).thenReturn(HttpStatus.OK);
		when(quantumClient.publishToQuantum(quantumData)).thenReturn(res);
	}
	
	@Test
	void testPublishToQuantumResettableStreamHttpServletRequest() {
		quantumService.publishToQuantum(request, response, msg);
		verify(quantumClient).publishToQuantum(quantumData);
	}

}		

##### CRONS ####	
https://www.freeformatter.com/cron-expression-generator-quartz.html
0 0 0 ? * * * >>> At 00:00:00am every day
0 0/01 13-16 * * * >> this runs between 1 pm to 4 pm ..every one min...	
					
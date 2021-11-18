* Liquibase maven plugin to generate SQL 
<plugin>
	<groupId>org.liquibase</groupId>
	<artifactId>liquibase-maven-plugin</artifactId>
	<configuration>
		<changeLogFile>
		  src/main/resources/db/changelog/db.changelog-master.yaml
		</changeLogFile>
		<driver>org.h2.Driver</driver>
		<url>jdbc:h2:mem:profile;DB_CLOSE_DELAY=-1</url>
		<username>TM_PROFILE</username>
		<password></password>
		</configuration>
		<version>3.6.2</version>
</plugin>
* mvn org.liquibase:liquibase-maven-plugin:updateSQL
* mvn org.liquibase:liquibase-maven-plugin:status

		
* Spring JPA Keywords for mongoDB
https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repository-query-keywords

* Spring Data JPA DELETE with Query 
@Modifying
	@Query("delete from TableB o where o.state in :status and o.createdDate < :date")
	void deleteByStateAndCreatedDateLessThan(@Param("status") List<String> status, @Param("date") Date date);

* spring data jpa - repository can have custom query like below to handle ignore null values from params
	@Query(" SELECT p FROM Photo p WHERE (:addressId is null or p.addressId = :addressId) and "
			+ "(:jobNumber is null or p.jobNumber = :jobNumber) and (:workOrder is null or p.workOrder = :workOrder) and "
			+ "(:accountNumber is null or p.accountNumber = :accountNumber)")
	List<Photo> findAllByAnyField(String addressId, String jobNumber, String workOrder, String accountNumber);
	
* Hibernate loglevel and l2 cache diable 
logging.level.org.hibernate.internal.util.EntityPrinter: info
hibernate.cache.use_second_level_cache: false  

* To externalize the logback xml through config property in yml or properties file
	logging.config: file:C:/Users/ffff/Desktop/logback-spring.xml

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
	@ComponentScan("com.xyz.acc.common.abc.*")
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

 ┌───────────── second (0-59)
 │ ┌───────────── minute (0 - 59)
 │ │ ┌───────────── hour (0 - 23)
 │ │ │ ┌───────────── day of the month (1 - 31)
 │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
 │ │ │ │ │ ┌───────────── day of the week (0 - 7)
 │ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
 │ │ │ │ │ │
 * * * * * *
 
 
differences between * and ?
To explain difference between ? and * in the expressions, first of all take a look at this table:

Field Name      Mandatory   Allowed Values      Allowed Special Characters
Seconds         YES         0-59                , - * /
Minutes         YES         0-59                , - * /
Hours           YES         0-23                , - * /
Day of month    YES         1-31                , - * ? / L W   //allowed '?'
Month           YES         1-12 or JAN-DEC     , - * /
Day of week     YES         1-7 or SUN-SAT      , - * ? / L #   //allowed '?'
Year            NO          empty, 1970-2099    , - * /
As you can see ? is only allowed in Day of month and Day of week is mandatory in one of both fields and will tell Quartz this value has not been defined, thus, use the other field (if you put ? into Day of month, the value used will be Day of week).

some of the example expressions to understand this 

Expression		Meaning				
second, minute, hour, day, month, weekday						
0 0 12 * * ? 		Fire at 12pm (noon) every day     				
0 15 10 ? * *     		 Fire at 10:15am every day   				
 0 15 10 * * ? 		Fire at 10:15am every day 				
0 15 10 * * ? * 		Fire at 10:15am every day       				
0 15 10 * * ? 2005 		Fire at 10:15am every day during the year 2005                 				
0 * 14 * * ?     		Fire every minute starting at 2pm and ending at 2:59pm, every day                          				
0 0/5 14 * * ? 		Fire every 5 minutes starting at 2pm and ending at 2:55pm, every day                                               				
0 0/5 14,18 * * ?		Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day				
0 0 0 ? * * * 		At 00:00:00am every day				
0 0/01 13-16 * * *		 this runs between 1 pm to 4 pm ..every one min... 				
 0 0 * * * *		the top of every hour of every day.				
*/10 * * * * *		every ten seconds				
 0 0 8-10 * * *		8, 9 and 10 o'clock of every day				
 0 0 6,19 * * *		 6:00 AM and 7:00 PM every day				
0 0/30 8-10 * * *		8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day				
 0 0 9-17 * * MON-FRI		on the hour nine-to-five weekdays				
0 0 0 25 12 ?		every Christmas Day at midnight
0 0/10 * * * *			Run every 10 mins
0 0 0/3 * * *                   Run every 3 hours

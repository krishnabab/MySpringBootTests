package com.krish.metrics.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "quantum.metrics.external.enabled", havingValue = "true", matchIfMissing = false)
public class FeignClientQuantumTagging extends Logger {

	@Value("${feign.client.config.default.logger-level:BASIC}")
	String logLevel;

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.valueOf(logLevel.toUpperCase());
		// return Logger.Level.FULL;
	}

	/*
	 * @Override protected void logRequest(String configKey, Level logLevel, Request
	 * request) { log.info("Logging from if "); log.info(configKey,
	 * "KRISH---> %s %s HTTP/1.1 (%s-byte body) ", request.httpMethod().name(),
	 * request.url(), request.body().length);
	 * log.info("Request url:"+request.url()); String reqBody = request.charset() !=
	 * null ? new String(request.body(), request.charset()) :
	 * "Can't Render Request Binary data"; log.info("Body:>>>>>>>>>>>>>>"+reqBody);
	 * }
	 * 
	 * @Override protected Response logAndRebufferResponse(String configKey, Level
	 * logLevel, Response response, long elapsedTime) throws IOException {
	 * log.info(configKey, "<--- %s %s HTTP/1.1 %s (%sms) ",
	 * response.request().httpMethod().name(), response.request().url(),
	 * response.status(), elapsedTime); byte[] bodyData =
	 * Util.toByteArray(response.body().asInputStream());
	 * log.info("Response body:"+new String(bodyData,
	 * response.request().charset())); return
	 * response.toBuilder().body(bodyData).build(); }
	 */

	@Override
	protected void logRequest(String configKey, Level logLevel, Request request) {
		/*
		 * NONE-0, BASIC-1, HEADERS-2, FULL - 3
		 */
		log.info("@Req _logLevel:" + logLevel.ordinal());
		log.info("Level.HEADERS.ordinal()" + Level.HEADERS.ordinal());
		super.logRequest(configKey, logLevel, request);

	}

	/*
	 * @Override protected Response logAndRebufferResponse(String configKey, Level
	 * logLevel, Response response, long elapsedTime) throws IOException {
	 * super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
	 * return response; }
	 */

	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
			throws IOException {
		FeignResponse feignResponse = new FeignResponse();
		int status = response.status();
		feignResponse.setStatus(response.status());
		feignResponse.setReason(
				response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason() : "");
		feignResponse.setTimeTaken(elapsedTime);

		// if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {}

		for (String field : response.headers().keySet()) {
			for (String value : Util.valuesOrEmpty(response.headers(), field)) {
				feignResponse.addHeader(field, value);
			}
		}

		if (response.body() != null) {
			byte[] bodyData = Util.toByteArray(response.body().asInputStream());
			if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyData.length > 0) {
				feignResponse.setBody(Util.decodeOrDefault(bodyData, Charset.defaultCharset(), "Binary data"));
			}
			log.info(String.format("%s <<==  Response : %s ", configKey, feignResponse));
			return response.toBuilder().body(bodyData).build();
		} else {
			log.info(String.format("%s <<==  Response : %s ", configKey, feignResponse));
		}
		//Code block to send data to Quantum Kafka
		return response;
	}

	@Setter
	private class FeignResponse {
		private int status;
		private String reason;
		private long timeTaken;
		private List<String> headers;
		private String body;

		public void addHeader(String key, String value) {
			if (headers == null) {
				headers = new ArrayList<>();
			}
			headers.add(String.format("%s: %s", key, value));
		}

		@Override
		public String toString() {
			return String.format(
					"Status = %s, Reason = %s, TimeTaken = %s, Headers = %s Body = %s, BodyLength = %s Bytes", status,
					reason, timeTaken, headers, body, (body != null && body.trim().length() > 0 ? body.length() : 0));
		}
	}

	@Override
	protected void log(String configKey, String format, Object... args) {
		log.info(format(configKey, format, args));
	}

	protected String format(String configKey, String format, Object... args) {
		return String.format(methodTag(configKey) + format, args);
	}
}
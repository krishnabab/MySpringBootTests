
package com.krish.quantum.monitoring;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "receivedTimestamp",
    "finishedCreatedTimestamp",
    "warp",
    "traceId",
    "message",
    "application",
    "source",
    "api",
    "error"
})
//@RequiredArgsConstructor
public class QuantumData {

    @JsonProperty("receivedTimestamp")
    private Long receivedTimestamp;
    @JsonProperty("finishedCreatedTimestamp")
    private Long finishedCreatedTimestamp;
    @JsonProperty("warp")
    private Warp warp;
    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("application")
    private Application application;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("api")
    private Api api;
    @JsonProperty("error")
    private Error error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("receivedTimestamp")
    public Long getReceivedTimestamp() {
        return receivedTimestamp;
    }

    @JsonProperty("receivedTimestamp")
    public void setReceivedTimestamp(Long receivedTimestamp) {
        this.receivedTimestamp = receivedTimestamp;
    }

    @JsonProperty("finishedCreatedTimestamp")
    public Long getFinishedCreatedTimestamp() {
        return finishedCreatedTimestamp;
    }

    @JsonProperty("finishedCreatedTimestamp")
    public void setFinishedCreatedTimestamp(Long finishedCreatedTimestamp) {
        this.finishedCreatedTimestamp = finishedCreatedTimestamp;
    }

    @JsonProperty("warp")
    public Warp getWarp() {
        return warp;
    }

    @JsonProperty("warp")
    public void setWarp(Warp warp) {
        this.warp = warp;
    }

    @JsonProperty("traceId")
    public String getTraceId() {
        return traceId;
    }

    @JsonProperty("traceId")
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    @JsonProperty("application")
    public Application getApplication() {
        return application;
    }

    @JsonProperty("application")
    public void setApplication(Application application) {
        this.application = application;
    }

    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @JsonProperty("error")
    public Error getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Error error) {
        this.error = error;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("receivedTimestamp", receivedTimestamp).append("finishedCreatedTimestamp", finishedCreatedTimestamp).append("warp", warp).append("traceId", traceId).append("message", message).append("application", application).append("source", source).append("api", api).append("error", error).append("additionalProperties", additionalProperties).toString();
    }

}

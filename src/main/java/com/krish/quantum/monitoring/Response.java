
package com.krish.quantum.monitoring;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "responseCode",
    "serviceResult",
    "responseTimeMs",
    "cached",
    "headers",
    "size",
    "body",
    "encBody"
})
public class Response {

    @JsonProperty("responseCode")
    private Integer responseCode;
    @JsonProperty("serviceResult")
    private String serviceResult;
    @JsonProperty("responseTimeMs")
    private Integer responseTimeMs;
    @JsonProperty("cached")
    private Boolean cached;
    @JsonProperty("headers")
    private Headers_ headers;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("body")
    private String body;
    @JsonProperty("encBody")
    private String encBody;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("responseCode")
    public Integer getResponseCode() {
        return responseCode;
    }

    @JsonProperty("responseCode")
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("serviceResult")
    public String getServiceResult() {
        return serviceResult;
    }

    @JsonProperty("serviceResult")
    public void setServiceResult(String serviceResult) {
        this.serviceResult = serviceResult;
    }

    @JsonProperty("responseTimeMs")
    public Integer getResponseTimeMs() {
        return responseTimeMs;
    }

    @JsonProperty("responseTimeMs")
    public void setResponseTimeMs(Integer responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    @JsonProperty("cached")
    public Boolean getCached() {
        return cached;
    }

    @JsonProperty("cached")
    public void setCached(Boolean cached) {
        this.cached = cached;
    }

    @JsonProperty("headers")
    public Headers_ getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(Headers_ headers) {
        this.headers = headers;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("encBody")
    public String getEncBody() {
        return encBody;
    }

    @JsonProperty("encBody")
    public void setEncBody(String encBody) {
        this.encBody = encBody;
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
        return new ToStringBuilder(this).append("responseCode", responseCode).append("serviceResult", serviceResult).append("responseTimeMs", responseTimeMs).append("cached", cached).append("headers", headers).append("size", size).append("body", body).append("encBody", encBody).append("additionalProperties", additionalProperties).toString();
    }

}

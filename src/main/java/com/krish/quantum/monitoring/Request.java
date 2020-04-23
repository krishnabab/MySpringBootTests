
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
    "httpVerb",
    "queryParameters",
    "encQueryParameters",
    "size",
    "headers",
    "body",
    "encBody"
})
public class Request {

    @JsonProperty("httpVerb")
    private String httpVerb;
    @JsonProperty("queryParameters")
    private String queryParameters;
    @JsonProperty("encQueryParameters")
    private String encQueryParameters;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("headers")
    private Headers headers;
    @JsonProperty("body")
    private String body;
    @JsonProperty("encBody")
    private String encBody;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("httpVerb")
    public String getHttpVerb() {
        return httpVerb;
    }

    @JsonProperty("httpVerb")
    public void setHttpVerb(String httpVerb) {
        this.httpVerb = httpVerb;
    }

    @JsonProperty("queryParameters")
    public String getQueryParameters() {
        return queryParameters;
    }

    @JsonProperty("queryParameters")
    public void setQueryParameters(String queryParameters) {
        this.queryParameters = queryParameters;
    }

    @JsonProperty("encQueryParameters")
    public String getEncQueryParameters() {
        return encQueryParameters;
    }

    @JsonProperty("encQueryParameters")
    public void setEncQueryParameters(String encQueryParameters) {
        this.encQueryParameters = encQueryParameters;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("headers")
    public Headers getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(Headers headers) {
        this.headers = headers;
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
        return new ToStringBuilder(this).append("httpVerb", httpVerb).append("queryParameters", queryParameters).append("encQueryParameters", encQueryParameters).append("size", size).append("headers", headers).append("body", body).append("encBody", encBody).append("additionalProperties", additionalProperties).toString();
    }

}

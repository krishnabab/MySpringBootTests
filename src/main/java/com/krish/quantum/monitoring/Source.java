
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
    "applicationName",
    "applicationType",
    "visitId",
    "userId",
    "accountNumber",
    "encAccountNumber"
})
public class Source {

    @JsonProperty("applicationName")
    private String applicationName;
    @JsonProperty("applicationType")
    private String applicationType;
    @JsonProperty("visitId")
    private String visitId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("encAccountNumber")
    private String encAccountNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("applicationName")
    public String getApplicationName() {
        return applicationName;
    }

    @JsonProperty("applicationName")
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @JsonProperty("applicationType")
    public String getApplicationType() {
        return applicationType;
    }

    @JsonProperty("applicationType")
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    @JsonProperty("visitId")
    public String getVisitId() {
        return visitId;
    }

    @JsonProperty("visitId")
    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("encAccountNumber")
    public String getEncAccountNumber() {
        return encAccountNumber;
    }

    @JsonProperty("encAccountNumber")
    public void setEncAccountNumber(String encAccountNumber) {
        this.encAccountNumber = encAccountNumber;
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
        return new ToStringBuilder(this).append("applicationName", applicationName).append("applicationType", applicationType).append("visitId", visitId).append("userId", userId).append("accountNumber", accountNumber).append("encAccountNumber", encAccountNumber).append("additionalProperties", additionalProperties).toString();
    }

}

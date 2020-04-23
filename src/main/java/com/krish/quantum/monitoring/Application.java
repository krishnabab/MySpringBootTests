
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
    "name",
    "clusterId",
    "instanceId",
    "instanceLocation",
    "instanceCpuUsage",
    "instanceMemoryUsage"
})
public class Application {

    @JsonProperty("name")
    private String name;
    @JsonProperty("clusterId")
    private String clusterId;
    @JsonProperty("instanceId")
    private String instanceId;
    @JsonProperty("instanceLocation")
    private String instanceLocation;
    @JsonProperty("instanceCpuUsage")
    private Double instanceCpuUsage;
    @JsonProperty("instanceMemoryUsage")
    private Double instanceMemoryUsage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("clusterId")
    public String getClusterId() {
        return clusterId;
    }

    @JsonProperty("clusterId")
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    @JsonProperty("instanceId")
    public String getInstanceId() {
        return instanceId;
    }

    @JsonProperty("instanceId")
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty("instanceLocation")
    public String getInstanceLocation() {
        return instanceLocation;
    }

    @JsonProperty("instanceLocation")
    public void setInstanceLocation(String instanceLocation) {
        this.instanceLocation = instanceLocation;
    }

    @JsonProperty("instanceCpuUsage")
    public Double getInstanceCpuUsage() {
        return instanceCpuUsage;
    }

    @JsonProperty("instanceCpuUsage")
    public void setInstanceCpuUsage(Double instanceCpuUsage) {
        this.instanceCpuUsage = instanceCpuUsage;
    }

    @JsonProperty("instanceMemoryUsage")
    public Double getInstanceMemoryUsage() {
        return instanceMemoryUsage;
    }

    @JsonProperty("instanceMemoryUsage")
    public void setInstanceMemoryUsage(Double instanceMemoryUsage) {
        this.instanceMemoryUsage = instanceMemoryUsage;
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
        return new ToStringBuilder(this).append("name", name).append("clusterId", clusterId).append("instanceId", instanceId).append("instanceLocation", instanceLocation).append("instanceCpuUsage", instanceCpuUsage).append("instanceMemoryUsage", instanceMemoryUsage).append("additionalProperties", additionalProperties).toString();
    }

}

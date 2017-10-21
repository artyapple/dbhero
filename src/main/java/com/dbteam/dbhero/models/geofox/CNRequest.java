
package com.dbteam.dbhero.models.geofox;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "theName",
    "maxList",
    "maxDistance",
    "coordinateType"
})
public class CNRequest {

    @JsonProperty("version")
    private Integer version;
    @JsonProperty("theName")
    private TheName theName;
    @JsonProperty("maxList")
    private Integer maxList;
    @JsonProperty("maxDistance")
    private Integer maxDistance;
    @JsonProperty("coordinateType")
    private String coordinateType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("theName")
    public TheName getTheName() {
        return theName;
    }

    @JsonProperty("theName")
    public void setTheName(TheName theName) {
        this.theName = theName;
    }

    @JsonProperty("maxList")
    public Integer getMaxList() {
        return maxList;
    }

    @JsonProperty("maxList")
    public void setMaxList(Integer maxList) {
        this.maxList = maxList;
    }

    @JsonProperty("maxDistance")
    public Integer getMaxDistance() {
        return maxDistance;
    }

    @JsonProperty("maxDistance")
    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    @JsonProperty("coordinateType")
    public String getCoordinateType() {
        return coordinateType;
    }

    @JsonProperty("coordinateType")
    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

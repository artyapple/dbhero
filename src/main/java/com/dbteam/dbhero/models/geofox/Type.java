
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
    "simpleType",
    "shortInfo",
    "longInfo",
    "model"
})
public class Type {

    @JsonProperty("simpleType")
    private String simpleType;
    @JsonProperty("shortInfo")
    private String shortInfo;
    @JsonProperty("longInfo")
    private String longInfo;
    @JsonProperty("model")
    private String model;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("simpleType")
    public String getSimpleType() {
        return simpleType;
    }

    @JsonProperty("simpleType")
    public void setSimpleType(String simpleType) {
        this.simpleType = simpleType;
    }

    @JsonProperty("shortInfo")
    public String getShortInfo() {
        return shortInfo;
    }

    @JsonProperty("shortInfo")
    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    @JsonProperty("longInfo")
    public String getLongInfo() {
        return longInfo;
    }

    @JsonProperty("longInfo")
    public void setLongInfo(String longInfo) {
        this.longInfo = longInfo;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
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

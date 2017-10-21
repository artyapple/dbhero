
package com.dbteam.dbhero.models.geofox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "city",
    "combinedName",
    "id",
    "type",
    "coordinate",
    "serviceTypes",
    "hasStationInformation",
    "depTime",
    "depDelay",
    "platform",
    "realtimePlatform"
})
public class From {

    @JsonProperty("name")
    private String name;
    @JsonProperty("city")
    private String city;
    @JsonProperty("combinedName")
    private String combinedName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinate")
    private Coordinate coordinate;
    @JsonProperty("serviceTypes")
    private List<String> serviceTypes = null;
    @JsonProperty("hasStationInformation")
    private Boolean hasStationInformation;
    @JsonProperty("depTime")
    private DepTime depTime;
    @JsonProperty("depDelay")
    private Integer depDelay;
    @JsonProperty("platform")
    private String platform;
    @JsonProperty("realtimePlatform")
    private String realtimePlatform;
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

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("combinedName")
    public String getCombinedName() {
        return combinedName;
    }

    @JsonProperty("combinedName")
    public void setCombinedName(String combinedName) {
        this.combinedName = combinedName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("coordinate")
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @JsonProperty("coordinate")
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @JsonProperty("serviceTypes")
    public List<String> getServiceTypes() {
        return serviceTypes;
    }

    @JsonProperty("serviceTypes")
    public void setServiceTypes(List<String> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    @JsonProperty("hasStationInformation")
    public Boolean getHasStationInformation() {
        return hasStationInformation;
    }

    @JsonProperty("hasStationInformation")
    public void setHasStationInformation(Boolean hasStationInformation) {
        this.hasStationInformation = hasStationInformation;
    }

    @JsonProperty("depTime")
    public DepTime getDepTime() {
        return depTime;
    }

    @JsonProperty("depTime")
    public void setDepTime(DepTime depTime) {
        this.depTime = depTime;
    }

    @JsonProperty("depDelay")
    public Integer getDepDelay() {
        return depDelay;
    }

    @JsonProperty("depDelay")
    public void setDepDelay(Integer depDelay) {
        this.depDelay = depDelay;
    }

    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @JsonProperty("realtimePlatform")
    public String getRealtimePlatform() {
        return realtimePlatform;
    }

    @JsonProperty("realtimePlatform")
    public void setRealtimePlatform(String realtimePlatform) {
        this.realtimePlatform = realtimePlatform;
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

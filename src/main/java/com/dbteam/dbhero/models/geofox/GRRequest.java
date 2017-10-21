
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
    "language",
    "start",
    "dest",
    "via",
    "time",
    "timeIsDeparture",
    "schedulesBefore",
    "schedulesAfter",
    "realtime"
})
public class GRRequest {

    @JsonProperty("version")
    private Integer version;
    @JsonProperty("language")
    private String language;
    @JsonProperty("start")
    private Start start;
    @JsonProperty("dest")
    private Destination dest;
    @JsonProperty("via")
    private Via via;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("timeIsDeparture")
    private Boolean timeIsDeparture;
    @JsonProperty("schedulesBefore")
    private Integer schedulesBefore;
    @JsonProperty("schedulesAfter")
    private Integer schedulesAfter;
    @JsonProperty("realtime")
    private String realtime;
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

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("start")
    public Start getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(Start start) {
        this.start = start;
    }

    @JsonProperty("dest")
    public Destination getDest() {
        return dest;
    }

    @JsonProperty("dest")
    public void setDest(Destination dest) {
        this.dest = dest;
    }

    @JsonProperty("via")
    public Via getVia() {
        return via;
    }

    @JsonProperty("via")
    public void setVia(Via via) {
        this.via = via;
    }

    @JsonProperty("time")
    public Time getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Time time) {
        this.time = time;
    }

    @JsonProperty("timeIsDeparture")
    public Boolean getTimeIsDeparture() {
        return timeIsDeparture;
    }

    @JsonProperty("timeIsDeparture")
    public void setTimeIsDeparture(Boolean timeIsDeparture) {
        this.timeIsDeparture = timeIsDeparture;
    }

    @JsonProperty("schedulesBefore")
    public Integer getSchedulesBefore() {
        return schedulesBefore;
    }

    @JsonProperty("schedulesBefore")
    public void setSchedulesBefore(Integer schedulesBefore) {
        this.schedulesBefore = schedulesBefore;
    }

    @JsonProperty("schedulesAfter")
    public Integer getSchedulesAfter() {
        return schedulesAfter;
    }

    @JsonProperty("schedulesAfter")
    public void setSchedulesAfter(Integer schedulesAfter) {
        this.schedulesAfter = schedulesAfter;
    }

    @JsonProperty("realtime")
    public String getRealtime() {
        return realtime;
    }

    @JsonProperty("realtime")
    public void setRealtime(String realtime) {
        this.realtime = realtime;
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

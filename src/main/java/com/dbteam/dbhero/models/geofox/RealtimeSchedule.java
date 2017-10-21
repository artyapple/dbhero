
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
    "routeId",
    "start",
    "dest",
    "time",
    "footpathTime",
    "tickets",
    "scheduleElements"
})
public class RealtimeSchedule {

    @JsonProperty("routeId")
    private Integer routeId;
    @JsonProperty("start")
    private Start start;
    @JsonProperty("dest")
    private Destination dest;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("footpathTime")
    private Integer footpathTime;
    @JsonProperty("tickets")
    private List<Ticket> tickets = null;
    @JsonProperty("scheduleElements")
    private List<ScheduleElement> scheduleElements = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("routeId")
    public Integer getRouteId() {
        return routeId;
    }

    @JsonProperty("routeId")
    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
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

    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    @JsonProperty("footpathTime")
    public Integer getFootpathTime() {
        return footpathTime;
    }

    @JsonProperty("footpathTime")
    public void setFootpathTime(Integer footpathTime) {
        this.footpathTime = footpathTime;
    }

    @JsonProperty("tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    @JsonProperty("tickets")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @JsonProperty("scheduleElements")
    public List<ScheduleElement> getScheduleElements() {
        return scheduleElements;
    }

    @JsonProperty("scheduleElements")
    public void setScheduleElements(List<ScheduleElement> scheduleElements) {
        this.scheduleElements = scheduleElements;
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

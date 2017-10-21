
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
    "returnCode",
    "realtimeSchedules",
    "individualTrack"
})
public class GRResponse {

    @JsonProperty("returnCode")
    private String returnCode;
    @JsonProperty("realtimeSchedules")
    private List<RealtimeSchedule> realtimeSchedules = null;
    @JsonProperty("individualTrack")
    private IndividualTrack individualTrack;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("returnCode")
    public String getReturnCode() {
        return returnCode;
    }

    @JsonProperty("returnCode")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @JsonProperty("realtimeSchedules")
    public List<RealtimeSchedule> getRealtimeSchedules() {
        return realtimeSchedules;
    }

    @JsonProperty("realtimeSchedules")
    public void setRealtimeSchedules(List<RealtimeSchedule> realtimeSchedules) {
        this.realtimeSchedules = realtimeSchedules;
    }

    @JsonProperty("individualTrack")
    public IndividualTrack getIndividualTrack() {
        return individualTrack;
    }

    @JsonProperty("individualTrack")
    public void setIndividualTrack(IndividualTrack individualTrack) {
        this.individualTrack = individualTrack;
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

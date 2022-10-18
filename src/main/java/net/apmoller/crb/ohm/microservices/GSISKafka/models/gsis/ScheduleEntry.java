package net.apmoller.crb.ohm.microservices.GSISKafka.models.gsis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntry {

    @JsonProperty("rotationId")
    private  String rotationId;
    @JsonProperty("rotationName")
    private String rotationName;
    @JsonProperty("rotationVersion")
    private String rotationVersion;
    @JsonProperty("scheduleEntryID")
    private List<ScheduleEntryID> scheduleEntryID;
    @JsonProperty("arrivalStatus")
    private String arrivalStatus;
    @JsonProperty("departureStatus")
    private String departureStatus;
    @JsonProperty("siteCallStatus")
    private String siteCallStatus;
    @JsonProperty("pH1")
    private String pH1;
    @JsonProperty("pH2")
    private String pH2;
    @JsonProperty("schedule")
    private List<Schedule> schedule;
    @JsonProperty("dummyCall")
    private String dummyCall;
    @JsonProperty("omitReason")
    private String omitReason;
    @JsonProperty("actual")
    private List<ActualStatus> actual;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("updatedBy")
    private String updatedBy;

}

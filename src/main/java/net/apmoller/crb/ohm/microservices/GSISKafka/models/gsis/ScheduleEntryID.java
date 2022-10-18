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
public class ScheduleEntryID {

    @JsonProperty("scheduleEntryKey")
    private String scheduleEntryKey;
    @JsonProperty("scheduleEntryIdentifier")
    private List<ScheduleEntryIdentifier> scheduleEntryIdentifier;

}

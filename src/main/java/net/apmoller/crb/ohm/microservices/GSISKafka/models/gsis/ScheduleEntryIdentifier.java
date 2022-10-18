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
public class ScheduleEntryIdentifier {
    @JsonProperty("vessel")
    private List<Vessel> vessel;
    @JsonProperty("arrivalVoyage")
    private List<Voyage> arrivalVoyage;
    @JsonProperty("departureVoyage")
    private List<Voyage> departureVoyage;
    @JsonProperty("arrivalService")
    private List<ArrivalDepartureService> arrivalService;
    @JsonProperty("departureService")
    private List<ArrivalDepartureService> departureService;
    @JsonProperty("previousPortCall")
    private List<PreviousPortCall> previousPortCall;
    @JsonProperty("currentPortCall")
    private List<CurrentPortCall> currentPortCall;
    @JsonProperty("nextPortCall")
    private List<NextPortCall> nextPortCall;
}

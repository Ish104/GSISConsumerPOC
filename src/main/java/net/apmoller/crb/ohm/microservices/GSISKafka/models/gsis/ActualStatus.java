package net.apmoller.crb.ohm.microservices.GSISKafka.models.gsis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualStatus {

    @JsonProperty("actualArrival")
    private String actualArrival;
    @JsonProperty("actualDeparture")
    private String actualDeparture;
    @JsonProperty("arrivalAtPilotStation")
    private String arrivalAtPilotStation;
    @JsonProperty("firstPilotOnBoard")
    private String firstPilotOnBoard;
    @JsonProperty("pilotOff")
    private String pilotOff;
}

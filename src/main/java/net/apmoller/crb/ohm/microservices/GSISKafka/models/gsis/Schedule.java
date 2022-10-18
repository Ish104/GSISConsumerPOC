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
public class Schedule {

    @JsonProperty("proformaArrival")
    private String proformaArrival;
    @JsonProperty("proformaDeparture")
    private String proformaDeparture;
    @JsonProperty("scheduledArrival")
    private String scheduledArrival;
    @JsonProperty("scheduledDeparture")
    private String scheduledDeparture;
}

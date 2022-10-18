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
public class NextPortCall {
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("terminalCode")
    private String terminalCode;
    @JsonProperty("cityName")
    private String cityName;
    @JsonProperty("terminalName")
    private String terminalName;
    @JsonProperty("cityGeoCode")
    private String cityGeoCode;
    @JsonProperty("terminalGeoCode")
    private String terminalGeoCode;
    @JsonProperty("previousScheduleEntryKey")
    private String previousScheduleEntryKey;
    @JsonProperty("arrivalVoyage")
    private String arrivalVoyage;
    @JsonProperty("departureVoyage")
    private String departureVoyage;
}

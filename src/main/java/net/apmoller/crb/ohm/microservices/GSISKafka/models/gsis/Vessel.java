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
public class Vessel {

    @JsonProperty("vesselCode")
    private String vesselCode;
    @JsonProperty("IMONumber")
    private String IMONumber;
    @JsonProperty("vesselName")
    private String vesselName;
    @JsonProperty("vesselOperatorCode")
    private String vesselOperatorCode;
    @JsonProperty("vesselFlag")
    private String vesselFlag;
    @JsonProperty("vesselCallSign")
    private String vesselCallSign;
}

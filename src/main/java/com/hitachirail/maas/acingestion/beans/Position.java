package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Position {

    private String parentVehicleId;
    private String vehicleId;
    private String tripId;
    private String sourceSystemId;
    private Integer type;
    private String operator;
    private String messageId;
    private Integer diagnosticStatus;
    private Double latitude;
    private Double longitude;
    private Long sysTimestamp;

}

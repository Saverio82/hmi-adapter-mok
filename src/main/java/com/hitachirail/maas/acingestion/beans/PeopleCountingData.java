package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PeopleCountingData {

    @JsonProperty("parent_vehicle_id")
    private String parentVehicleId;

    @JsonProperty("vehicle_id")
    private String vehicleId;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("sys_ts")
    private long sysTimestamp;

    @JsonProperty("msg_id")
    private String messageId;

    @JsonProperty("in")
    private Integer peopleIn;

    @JsonProperty("out")
    private Integer peopleOut;

    @JsonProperty("count")
    private Integer peopleCount;

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("close_time")
    private Long closeTime;

    @JsonProperty("diagnostic_status")
    private int diagnosticStatus;

}

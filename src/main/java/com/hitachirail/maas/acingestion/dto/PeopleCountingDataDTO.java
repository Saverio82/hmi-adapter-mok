package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonIOException;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PeopleCountingDataDTO {

    @JsonProperty("parent_vehicle_id")
    private String parentVehicleId;
    @NotNull
    @JsonProperty("vehicle_id")
    private String vehicleId;
    @JsonProperty("trip_id")
    private String tripId;
    @JsonProperty("source_system_id")
    private String sourceSystemId;
    @NotNull
    @JsonProperty("type")
    private Integer type;
    @NotNull
    @JsonProperty("operator")
    private String operator;
    @NotNull
    @JsonProperty("sys_ts")
    private Long sysTimestamp;
    @NotNull
    @JsonProperty("msg_id")
    private String messageId;
    @NotNull
    @JsonProperty("in")
    private Integer peopleIn;
    @NotNull
    @JsonProperty("out")
    private Integer peopleOut;
    @NotNull
    @JsonProperty("count")
    private Integer peopleCount;
    @JsonProperty("stop_id")
    private String stopId;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lng")
    private Double longitude;
    @JsonProperty("open_time")
    private Long openTime;
    @NotNull
    @JsonProperty("close_time")
    private Long closeTime;
    @NotNull
    @JsonProperty("diagnostic_status")
    private Integer diagnosticStatus;

}

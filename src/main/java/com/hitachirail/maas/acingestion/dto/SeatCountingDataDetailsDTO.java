package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SeatCountingDataDetailsDTO {

    @NotNull
    @JsonProperty("sensor_id")
    private String sensorId;
    @NotNull
    @JsonProperty("sensor_type")
    private Integer sensorType;
    @NotNull
    @JsonProperty("status")
    private Integer status;
    @NotNull
    @JsonProperty("seat_ts")
    private Long seatTimestamp;

}

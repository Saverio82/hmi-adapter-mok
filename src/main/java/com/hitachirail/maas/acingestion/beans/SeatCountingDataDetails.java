package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SeatCountingDataDetails {

    private String sensorId;
    private Integer sensorType;
    private Integer status;
    private Long seatTimestamp;

    @JsonCreator
    public SeatCountingDataDetails(@JsonProperty(value = "sensor_id", required = true) String sensorId,
                                   @JsonProperty(value = "sensor_type", required = true) Integer sensorType,
                                   @JsonProperty(value = "status", required = true) Integer status,
                                   @JsonProperty(value = "seat_ts", required = true) Long seatTimestamp) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.status = status;
        this.seatTimestamp = seatTimestamp;
    }

}

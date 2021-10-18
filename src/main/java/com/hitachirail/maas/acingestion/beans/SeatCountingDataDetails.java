package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SeatCountingDataDetails {

    @JsonProperty("sensor_is")
    private String sensorIS;

    @JsonProperty("sensor_type")
    private Integer sensorType;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("seat_ts")
    private Long seatTimestamp;

}

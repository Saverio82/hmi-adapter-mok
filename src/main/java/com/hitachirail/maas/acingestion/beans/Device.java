package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Device {

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("ble_ts")
    private Long bleTs;

}

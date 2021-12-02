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
public class DeviceDTO {

    @NotNull
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("stop_id")
    private String stopId;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lng")
    private Double longitude;
    @NotNull
    @JsonProperty("status")
    private Integer status;
    @NotNull
    @JsonProperty("ble_ts")
    private Long bleTs;


}

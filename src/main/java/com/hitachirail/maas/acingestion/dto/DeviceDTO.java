package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonIOException;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceDTO {

    private String deviceId;
    private String stopId;
    private Double latitude;
    private Double longitude;
    private Integer status;
    private Long bleTs;

    @JsonCreator
    public DeviceDTO(@JsonProperty(value = "device_id", required = true) String deviceId,
                     @JsonProperty("stop_id") String stopId,
                     @JsonProperty("lat") Double latitude,
                     @JsonProperty("lng") Double longitude,
                     @JsonProperty(value = "status", required = true) Integer status,
                     @JsonProperty(value = "ble_ts", required = true) Long bleTs) {
        this.deviceId = deviceId;
        this.stopId = stopId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.bleTs = bleTs;

        if(this.stopId == null && (this.latitude == null || this.longitude == null)) {
            throw new JsonIOException("stopId, latitude and longitude cannot all be null");
        }

    }

}

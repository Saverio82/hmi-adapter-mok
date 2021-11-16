package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

    @JsonCreator
    public Position(
            @JsonProperty("parent_vehicle_id") String parentVehicleId,
            @JsonProperty(value = "vehicle_id", required = true) String vehicleId,
            @JsonProperty("trip_id") String tripId,
            @JsonProperty("source_system_id") String sourceSystemId,
            @JsonProperty(value = "type", required = true) Integer type,
            @JsonProperty(value = "operator", required = true) String operator,
            @JsonProperty(value = "msg_id", required = true) String messageId,
            @JsonProperty(value = "diagnostic_status", required = true) Integer diagnosticStatus,
            @JsonProperty(value = "lat", required = true) Double latitude,
            @JsonProperty(value = "lng", required = true) Double longitude,
            @JsonProperty(value = "sys_ts", required = true) Long sysTimestamp
    ) {
        this.parentVehicleId = parentVehicleId;
        this.vehicleId = vehicleId;
        this.tripId = tripId;
        this.sourceSystemId = sourceSystemId;
        this.type = type;
        this.operator = operator;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sysTimestamp = sysTimestamp;
    }

}

package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonIOException;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PeopleCountingDataDTO {

    private String parentVehicleId;
    private String vehicleId;
    private String tripId;
    private String sourceSystemId;
    private Integer type;
    private String operator;
    private Long sysTimestamp;
    private String messageId;
    private Integer peopleIn;
    private Integer peopleOut;
    private Integer peopleCount;
    private String stopId;
    private Double latitude;
    private Double longitude;
    private Long openTime;
    private Long closeTime;
    private Integer diagnosticStatus;

    @JsonCreator
    public PeopleCountingDataDTO(@JsonProperty("parent_vehicle_id") String parentVehicleId,
                                 @JsonProperty(value = "vehicle_id", required = true) String vehicleId,
                                 @JsonProperty("trip_id") String tripId,
                                 @JsonProperty("source_system_id") String sourceSystemId,
                                 @JsonProperty(value = "type", required = true) Integer type,
                                 @JsonProperty(value = "operator", required = true) String operator,
                                 @JsonProperty(value = "sys_ts", required = true) Long sysTimestamp,
                                 @JsonProperty(value = "msg_id", required = true) String messageId,
                                 @JsonProperty(value = "in", required = true) Integer peopleIn,
                                 @JsonProperty(value = "out", required = true) Integer peopleOut,
                                 @JsonProperty(value = "count", required = true) Integer peopleCount,
                                 @JsonProperty("stop_id") String stopId,
                                 @JsonProperty("lat") Double latitude,
                                 @JsonProperty("lng") Double longitude,
                                 @JsonProperty(value = "open_time") Long openTime,
                                 @JsonProperty(value = "close_time", required = true) Long closeTime,
                                 @JsonProperty(value = "diagnostic_status", required = true) Integer diagnosticStatus) {
        this.parentVehicleId = parentVehicleId;
        this.vehicleId = vehicleId;
        this.tripId = tripId;
        this.sourceSystemId = sourceSystemId;
        this.type = type;
        this.operator = operator;
        this.sysTimestamp = sysTimestamp;
        this.messageId = messageId;
        this.peopleIn = peopleIn;
        this.peopleOut = peopleOut;
        this.peopleCount = peopleCount;
        this.stopId = stopId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.diagnosticStatus = diagnosticStatus;

        if(this.stopId == null && (this.latitude == null || this.longitude == null)) {
            throw new JsonIOException("stopId, latitude and longitude cannot all be null");
        }
    }

}

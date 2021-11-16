package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BluetoothCountingData {

    private String parentVehicleId;
    private String vehicleId;
    private String tripId;
    private String sourceSystemId;
    private Integer type;
    private String operator;
    private String messageId;
    private Integer diagnosticStatus;
    private Long sysTimestamp;
    private List<Device> devices;

    @JsonCreator
    public BluetoothCountingData(@JsonProperty("parent_vehicle_id") String parentVehicleId,
                                 @JsonProperty(value = "vehicle_id", required = true) String vehicleId,
                                 @JsonProperty("trip_id") String tripId,
                                 @JsonProperty("source_system_id") String sourceSystemId,
                                 @JsonProperty(value = "type", required = true) Integer type,
                                 @JsonProperty(value = "operator", required = true) String operator,
                                 @JsonProperty(value = "msg_id", required = true) String messageId,
                                 @JsonProperty(value = "diagnostic_status", required = true) Integer diagnosticStatus,
                                 @JsonProperty(value = "sys_ts", required = true) Long sysTimestamp,
                                 @JsonProperty(value = "updates", required = true) List<Device> devices) {
        this.parentVehicleId = parentVehicleId;
        this.vehicleId = vehicleId;
        this.tripId = tripId;
        this.sourceSystemId = sourceSystemId;
        this.type = type;
        this.operator = operator;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.sysTimestamp = sysTimestamp;
        this.devices = devices;
    }

}

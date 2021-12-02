package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BluetoothCountingDataDTO {

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
    @JsonProperty("msg_id")
    private String messageId;
    @NotNull
    @JsonProperty("diagnostic_status")
    private Integer diagnosticStatus;
    @NotNull
    @JsonProperty("sys_ts")
    private Long sysTimestamp;
    @NotNull
    @JsonProperty("updates")
    private List<DeviceDTO> deviceDTOS;

}

package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BluetoothCountingData {

    @JsonProperty("parent_vehicle_id")
    private String parentVehicleId;

    @JsonProperty("vehicle_id")
    private String vehicleId;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("msg_id")
    private String messageId;

    @JsonProperty("diagnostic_status")
    private int diagnosticStatus;

    @JsonProperty("sys_ts")
    private Long sysTimestamp;

    @JsonProperty("updates")
    private List<Device> devices;

}

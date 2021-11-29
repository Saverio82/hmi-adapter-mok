package com.hitachirail.maas.acingestion.beans;

import lombok.*;

import java.util.List;

@NoArgsConstructor
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

}

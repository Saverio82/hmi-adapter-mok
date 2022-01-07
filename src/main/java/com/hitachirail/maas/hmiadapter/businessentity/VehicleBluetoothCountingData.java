package com.hitachirail.maas.hmiadapter.businessentity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehicleBluetoothCountingData extends BluetoothCountingData{

    private String vehicleId;

    @Builder
    public VehicleBluetoothCountingData(Long tenantId, String serviceJourneyId, String sourceSystemId,
                                        String messageId, Integer diagnosticStatus, Long sysTimestamp, String deviceId,
                                        String scheduledStopPointId, Double latitude, Double longitude, Integer status,
                                        Long bleTs, String vehicleId) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId, diagnosticStatus, sysTimestamp, deviceId, scheduledStopPointId, latitude, longitude, status, bleTs);
        this.vehicleId = vehicleId;
    }

    public VehicleBluetoothCountingData() {
        super.setTenantId(1L);
        super.setServiceJourneyId("21");
        super.setSourceSystemId("AR");
        super.setMessageId("b5a4004e-cf2f-4845-936e-855d0e3031a5");
        super.setDiagnosticStatus(0);
        super.setLatitude(1.2);
        super.setLongitude(2.3);
        super.setStatus(1);
        super.setBleTs(2L);
        this.vehicleId = "2616";


    }
}

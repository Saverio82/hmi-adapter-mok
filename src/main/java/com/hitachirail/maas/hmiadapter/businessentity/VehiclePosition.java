package com.hitachirail.maas.hmiadapter.businessentity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehiclePosition extends Position {

    private String vehicleId;

    @Builder
    public VehiclePosition(String vehicleId, Long tenantId, String serviceJourneyId, String sourceSystemId, String messageId,
                           Integer diagnosticStatus, Double latitude, Double longitude, Long sysTimestamp) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId,
                diagnosticStatus, latitude, longitude,
                sysTimestamp);
        this.vehicleId = vehicleId;

    }


    public VehiclePosition() {

        super.setTenantId(2L);
        super.setServiceJourneyId("21");
        super.setSourceSystemId("AR");
        super.setMessageId("b5a4004e-cf2f-4845-936e-855d0e3031a5");
        super.setDiagnosticStatus(0);
        super.setLatitude(1.2);
        super.setLongitude(2.3);
        super.setSysTimestamp(112312L);
        this.vehicleId = "34";
    }


}

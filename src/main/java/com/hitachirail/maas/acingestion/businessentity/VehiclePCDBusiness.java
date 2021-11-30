package com.hitachirail.maas.acingestion.businessentity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehiclePCDBusiness extends PCDBusiness{

    private String vehicleId;

    @Builder
    public VehiclePCDBusiness(Long tenantId, String serviceJourneyId, String sourceSystemId, String messageId, Integer diagnosticStatus, Double latitude, Double longitude, Integer peopleIn, Integer peopleOut, Integer peopleCount, Long sysTimestamp, Long openTime, Long closeTime, String vehicleId) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId, diagnosticStatus, latitude, longitude, peopleIn, peopleOut, peopleCount, sysTimestamp, openTime, closeTime);
        this.vehicleId = vehicleId;
    }

    public VehiclePCDBusiness(){};
}

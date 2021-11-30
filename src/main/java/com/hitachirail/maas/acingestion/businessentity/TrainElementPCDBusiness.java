package com.hitachirail.maas.acingestion.businessentity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrainElementPCDBusiness extends PCDBusiness{

    private String trainElementId;
    private String trainId;

    @Builder
    public TrainElementPCDBusiness(Long tenantId, String serviceJourneyId, String sourceSystemId, String messageId, Integer diagnosticStatus, Double latitude, Double longitude, Integer peopleIn, Integer peopleOut, Integer peopleCount, Long sysTimestamp, Long openTime, Long closeTime, String trainElementId, String trainId) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId, diagnosticStatus, latitude, longitude, peopleIn, peopleOut, peopleCount, sysTimestamp, openTime, closeTime);
        this.trainElementId = trainElementId;
        this.trainId = trainId;
    }

    public TrainElementPCDBusiness(){}
}

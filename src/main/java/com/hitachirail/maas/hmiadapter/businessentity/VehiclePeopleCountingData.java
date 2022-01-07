package com.hitachirail.maas.hmiadapter.businessentity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehiclePeopleCountingData extends PeopleCountingData {

    private String vehicleId;

    @Builder
    public VehiclePeopleCountingData(Long tenantId, String serviceJourneyId, String sourceSystemId, String messageId, Integer diagnosticStatus, Double latitude, Double longitude, Integer peopleIn, Integer peopleOut, Integer peopleCount, Long sysTimestamp,
                                     Long openTime, Long closeTime, String vehicleId, String scheduledStopPointId) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId, diagnosticStatus, latitude, longitude, peopleIn, peopleOut, peopleCount, sysTimestamp, openTime, closeTime, scheduledStopPointId);
        this.vehicleId = vehicleId;
    }

    public VehiclePeopleCountingData(){
        super.setTenantId(1L);
        super.setServiceJourneyId("21");
        super.setSourceSystemId("AR");
        super.setMessageId("b5a4004e-cf2f-4845-936e-855d0e3031a5");
        super.setDiagnosticStatus(0);
        super.setLatitude(1.2);
        super.setLongitude(2.3);
        super.setPeopleCount(23);
        super.setPeopleIn(1);
        super.setPeopleIn(3);
        this.vehicleId = "2616";



    };
}

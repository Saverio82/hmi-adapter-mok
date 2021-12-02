package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    }


}

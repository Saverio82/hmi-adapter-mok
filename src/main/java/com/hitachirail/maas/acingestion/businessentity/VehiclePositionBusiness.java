package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehiclePositionBusiness extends PositionBusiness {

    private String vehicleId;



    @JsonCreator
    @Builder
    public VehiclePositionBusiness(@JsonProperty("vehicle_id")String vehicleId,
                           @JsonProperty("tenant_id")Long tenantId,
                           @JsonProperty("service_journey_id")String serviceJourneyId,
                           @JsonProperty("source_system_id")String sourceSystemId,
                           @JsonProperty("message_id")String messageId,
                           @JsonProperty("diagnostic_status")Integer diagnosticStatus,
                           @JsonProperty("latitude")Double latitude,
                           @JsonProperty("longitude")Double longitude,
                           @JsonProperty("sys_timestamp")Long sysTimestamp) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId,
                diagnosticStatus, latitude, longitude,
                sysTimestamp);
        this.vehicleId = vehicleId;

    }


    public VehiclePositionBusiness() {
    }


}

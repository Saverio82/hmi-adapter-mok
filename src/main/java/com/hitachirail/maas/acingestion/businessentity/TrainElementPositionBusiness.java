package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrainElementPositionBusiness extends BusinessPosition {

    private String trainElementId;
    private String trainId;


    @JsonCreator
    @Builder
    public TrainElementPositionBusiness(@JsonProperty("train_element_id")String trainElementId,
                                        @JsonProperty("tenant_id")Long tenantId,
                                        @JsonProperty("train_id")String trainId,
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
        this.trainElementId = trainElementId;
        this.trainId = trainId;
        /*
        this.serviceJourneyId = serviceJourneyId;
        this.sourceSystemId = sourceSystemId;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sysTimestamp = sysTimestamp; */
    }

    public TrainElementPositionBusiness(){};

}

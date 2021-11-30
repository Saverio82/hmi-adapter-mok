package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrainElementPosition extends Position {

    private String trainElementId;
    private String trainId;


    @Builder
    public TrainElementPosition(String trainElementId, Long tenantId, String trainId, String serviceJourneyId, String sourceSystemId, String messageId,
                                Integer diagnosticStatus, Double latitude, Double longitude, Long sysTimestamp) {
        super(tenantId, serviceJourneyId, sourceSystemId, messageId,
                diagnosticStatus, latitude, longitude,
                sysTimestamp);
        this.trainElementId = trainElementId;
        this.trainId = trainId;
    }

    public TrainElementPosition(){};

}

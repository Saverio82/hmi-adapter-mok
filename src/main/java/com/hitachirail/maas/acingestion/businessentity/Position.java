package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public  class Position {

    private Long tenantId;
    private String serviceJourneyId;
    private String sourceSystemId;
    private String messageId;
    private Integer diagnosticStatus;
    private Double latitude;
    private Double longitude;
    private Long sysTimestamp;

    @JsonCreator
    public Position(Long tenantId, String serviceJourneyId, String sourceSystemId, String messageId, Integer diagnosticStatus,
                    Double latitude, Double longitude, Long sysTimestamp) {

        this.tenantId = tenantId;
        this.serviceJourneyId = serviceJourneyId;
        this.sourceSystemId = sourceSystemId;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sysTimestamp = sysTimestamp;
    }

    public Position() {
    }


}

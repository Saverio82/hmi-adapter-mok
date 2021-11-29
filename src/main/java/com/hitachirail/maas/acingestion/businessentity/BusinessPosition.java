package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public  class BusinessPosition {

    private Long tenantId;
    private String serviceJourneyId;
    private String sourceSystemId;
    private String messageId;
    private Integer diagnosticStatus;
    private Double latitude;
    private Double longitude;
    private Long sysTimestamp;

    @JsonCreator
    public BusinessPosition(@JsonProperty("tenant_id")Long tenantId,
                            @JsonProperty("service_journey_id")String serviceJourneyId,
                            @JsonProperty("source_system_id")String sourceSystemId,
                            @JsonProperty("message_id")String messageId,
                            @JsonProperty("diagnostic_status")Integer diagnosticStatus,
                            @JsonProperty("latitude")Double latitude,
                            @JsonProperty("longitude")Double longitude,
                            @JsonProperty("sys_timestamp")Long sysTimestamp) {

        this.tenantId = tenantId;
        this.serviceJourneyId = serviceJourneyId;
        this.sourceSystemId = sourceSystemId;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sysTimestamp = sysTimestamp;
    }

    public BusinessPosition() {
    }


    ;

}

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
    private Long tenantId;
    private String trainId;
    private String serviceJourneyId;
    private String sourceSystemId;
    private String messageId;
    private Integer diagnosticStatus;
    private Double latitude;
    private Double longitude;
    private Long sysTimestamp;

    @JsonCreator
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
        super(serviceJourneyId, sourceSystemId, messageId,
                diagnosticStatus, latitude, longitude,
                sysTimestamp);
        this.trainElementId = trainElementId;
        this.tenantId = tenantId;
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

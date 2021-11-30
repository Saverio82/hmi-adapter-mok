package com.hitachirail.maas.acingestion.businessentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PCDBusiness {

    private Long tenantId;
    private String serviceJourneyId;
    private String sourceSystemId;
    private String messageId;
    private Integer diagnosticStatus;
    private Double latitude;
    private Double longitude;
    private Integer peopleIn;
    private Integer peopleOut;
    private Integer peopleCount;
    private Long sysTimestamp;
    private Long openTime;
    private Long closeTime;

    public PCDBusiness(@JsonProperty("tenant_id")Long tenantId,
                       @JsonProperty("service_journey_id")String serviceJourneyId,
                       @JsonProperty("source_system_id")String sourceSystemId,
                       @JsonProperty("message_id")String messageId,
                       @JsonProperty("diagnostic_status")Integer diagnosticStatus,
                       @JsonProperty("latitude")Double latitude,
                       @JsonProperty("longitude")Double longitude,
                       @JsonProperty("people_in")Integer peopleIn,
                       @JsonProperty("people_out")Integer peopleOut,
                       @JsonProperty("people_count")Integer peopleCount,
                       @JsonProperty("sys_timestamp")Long sysTimestamp,
                       @JsonProperty("open_time")Long openTime,
                       @JsonProperty("close_time")Long closeTime){

        this.tenantId = tenantId;
        this.serviceJourneyId = serviceJourneyId;
        this.sourceSystemId = sourceSystemId;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.peopleIn = peopleIn;
        this.peopleOut = peopleOut;
        this.peopleCount = peopleCount;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.sysTimestamp = sysTimestamp;
    }

    public PCDBusiness(){}
}

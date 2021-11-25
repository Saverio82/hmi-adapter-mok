package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StationCongestionDTO {

    private String stopId;
    private String parentStopId;
    private String sourceSystemId;
    private String operator;
    private String messageId;
    private Integer diagnosticStatus;
    private Integer peopleIn;
    private Integer peopleOut;
    private Integer peopleCount;
    private Long sysTimestamp;

    @JsonCreator
    public StationCongestionDTO(@JsonProperty(value = "stop_id", required = true) String stopId,
                                @JsonProperty("parent_stop_id") String parentStopId,
                                @JsonProperty("source_system_id") String sourceSystemId,
                                @JsonProperty(value = "operator", required = true) String operator,
                                @JsonProperty(value = "msg_id", required = true) String messageId,
                                @JsonProperty(value = "diagnostic_status", required = true) Integer diagnosticStatus,
                                @JsonProperty(value = "in", required = true) Integer peopleIn,
                                @JsonProperty(value = "out", required = true) Integer peopleOut,
                                @JsonProperty(value = "count", required = true) Integer peopleCount,
                                @JsonProperty(value = "sys_ts", required = true) Long sysTimestamp) {
        this.stopId = stopId;
        this.parentStopId = parentStopId;
        this.sourceSystemId = sourceSystemId;
        this.operator = operator;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.peopleIn = peopleIn;
        this.peopleOut = peopleOut;
        this.peopleCount = peopleCount;
        this.sysTimestamp = sysTimestamp;
    }

}

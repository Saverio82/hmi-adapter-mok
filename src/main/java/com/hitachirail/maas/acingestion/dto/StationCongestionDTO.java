package com.hitachirail.maas.acingestion.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StationCongestionDTO {

    @NotNull
    @JsonProperty("stop_id")
    private String scheduledStopPointId;
    @NotNull
    @JsonProperty("parent_stop_id")
    private String parentStopId;
    @NotNull
    @JsonProperty("source_system_id")
    private String sourceSystemId;
    @NotNull
    @JsonProperty("operator")
    private String operator;
    @NotNull
    @JsonProperty("msg_id")
    private String messageId;
    @NotNull
    @JsonProperty("diagnostic_status")
    private Integer diagnosticStatus;
    @NotNull
    @JsonProperty("in")
    private Integer peopleIn;
    @NotNull
    @JsonProperty("out")
    private Integer peopleOut;
    @NotNull
    @JsonProperty("count")
    private Integer peopleCount;
    @NotNull
    @JsonProperty("sys_ts")
    private Long sysTimestamp;

}

package com.hitachirail.maas.acingestion.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class StationCongestion {

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("parent_stop_id")
    private String parentStopId;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("msg_id")
    private String messageId;

    @JsonProperty("diagnostic_status")
    private String diagnosticStatus;

    @JsonProperty("in")
    private String peopleIn;

    @JsonProperty("out")
    private String peopleOut;

    @JsonProperty("count")
    private String peopleCount;

    @JsonProperty("sys_ts")
    private long sysTimestamp;

}

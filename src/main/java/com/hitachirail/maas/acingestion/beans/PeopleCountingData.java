package com.hitachirail.maas.acingestion.beans;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PeopleCountingData {

    private String parentVehicleId;
    private String vehicleId;
    private String tripId;
    private String sourceSystemId;
    private Integer type;
    private String operator;
    private Long sysTimestamp;
    private String messageId;
    private Integer peopleIn;
    private Integer peopleOut;
    private Integer peopleCount;
    private String stopId;
    private Double latitude;
    private Double longitude;
    private Long openTime;
    private Long closeTime;
    private Integer diagnosticStatus;

}

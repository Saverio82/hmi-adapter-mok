package com.hitachirail.maas.acingestion.businessentity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BusinessSeatCountingDataAggregate {

    private String parentVehicleId;
    private String vehicleId;
    private String tripId;
    private String sourceSystemId;
    private Integer type;
    private String operator;
    private Integer seatingOccupancy;
    private Long sysTimestamp;
    private String messageId;
    private Integer diagnosticStatus;
    private List<BusinessSeatCountingDataDetails> seatCountingDataDetailsList;

}

package com.hitachirail.maas.acingestion.businessentity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BusinessSeatCountingDataDetails {

    private String sensorId;
    private Integer sensorType;
    private Integer status;
    private Long seatTimestamp;

}

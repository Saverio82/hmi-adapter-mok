package com.hitachirail.maas.acingestion.businessentity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BusinessDevice {

    private String deviceId;
    private String stopId;
    private Double latitude;
    private Double longitude;
    private Integer status;
    private Long bleTs;

}

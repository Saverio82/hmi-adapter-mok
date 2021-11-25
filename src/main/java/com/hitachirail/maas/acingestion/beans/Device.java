package com.hitachirail.maas.acingestion.beans;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Device {

    private String deviceId;
    private String stopId;
    private Double latitude;
    private Double longitude;
    private Integer status;
    private Long bleTs;

}

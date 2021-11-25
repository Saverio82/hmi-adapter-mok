package com.hitachirail.maas.acingestion.streaming.consumer.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BusinessObjectWrapper <T> {
    private Integer type;
    private T BusinessObject;


}

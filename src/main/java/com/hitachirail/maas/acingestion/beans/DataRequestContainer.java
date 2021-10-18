package com.hitachirail.maas.acingestion.beans;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@Data
public class DataRequestContainer<T> {

    private String topic;
    private List<T> data;

}

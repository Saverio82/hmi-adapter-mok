package com.hitachirail.maas.acingestion.controller.beans;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@Data
public class DataRequestContainer<T> {

    private List<T> data;

}

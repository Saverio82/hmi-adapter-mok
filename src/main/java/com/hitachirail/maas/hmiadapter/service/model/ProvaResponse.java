package com.hitachirail.maas.hmiadapter.service.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProvaResponse {
    private String name;

    public ProvaResponse(String name) {
        this.name = name;
    }
}

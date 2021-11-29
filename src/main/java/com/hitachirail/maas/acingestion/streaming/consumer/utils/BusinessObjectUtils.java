package com.hitachirail.maas.acingestion.streaming.consumer.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BusinessObjectUtils {

    private static final String AMTOPERATOR = "AMT";
    private Map tenatIdMap = new HashMap<String, Long>();

    public Long getTenantId(String operator){
        this.stubPopulateTenantIdMap(AMTOPERATOR, 1L);
        return (Long)tenatIdMap.get(operator);
    }

    public void stubPopulateTenantIdMap(String key, Long value){
        tenatIdMap.put(key, value);

    }
}

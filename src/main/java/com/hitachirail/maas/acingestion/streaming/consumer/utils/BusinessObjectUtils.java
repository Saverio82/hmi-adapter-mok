package com.hitachirail.maas.acingestion.streaming.consumer.utils;

import com.hitachirail.maas.acingestion.businessentity.TenantManagement;
import com.hitachirail.maas.acingestion.client.MonitoringCoreClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BusinessObjectUtils {

    public MonitoringCoreClient client;

    BusinessObjectUtils(MonitoringCoreClient client){
        this.client = client;
    }

    private Map tenatIdMap = new HashMap<String, Long>();

    public Long getTenantId(String operator){
        return (Long)tenatIdMap.get(operator);
    }

    @PostConstruct
    public void fillTenantIdMap(){
        Map<String, Long> agencyNamesList = client.getTenantManagement().stream()
                .collect(Collectors.toMap(TenantManagement::getAgencyNames, t -> t.getId()));
        agencyNamesList.forEach((k, v) -> {
            List<String> names = Arrays.asList(k.split(","));
            for(String name : names)
                tenatIdMap.put(name, v);
        });

        }


}

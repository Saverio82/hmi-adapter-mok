package com.hitachirail.maas.hmiadapter.controller;


import com.hitachirail.maas.hmiadapter.businessentity.*;


import com.hitachirail.maas.hmiadapter.service.model.Prova;
import com.hitachirail.maas.hmiadapter.service.model.ProvaResponse;
import com.hitachirail.maas.hmiadapter.service.model.User;
import net.bytebuddy.build.BuildLogger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PushMessages<T>  {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SimpUserRegistry simpUserRegistry;
    private final HashMap<Integer,List<T>> map;



    public PushMessages(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry, HashMap<Integer, List<T>> map) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
        this.map = map;
        List<TrainElementBluetoothCountingData> trainElementBCDList = new ArrayList<>();
        trainElementBCDList.add(new TrainElementBluetoothCountingData());
        map.put(1, (List<T>) trainElementBCDList);
        List <VehicleBluetoothCountingData> vehicleBCDList = new ArrayList<>();
        vehicleBCDList.add(new VehicleBluetoothCountingData());
        map.put(2, (List<T>) vehicleBCDList);
        List <VehiclePeopleCountingData> vehiclePCDList = new ArrayList<>();
        vehiclePCDList.add(new VehiclePeopleCountingData());
        map.put(3, (List<T>) vehiclePCDList);
        List <VehiclePosition> vehiclePositionList = new ArrayList<>();
        vehiclePositionList.add(new VehiclePosition());
        map.put(4, (List<T>) vehiclePositionList);
        List<VehicleSeatCountingDataAggregate> vehicleSeatCountingDataAggregateList = new ArrayList<>();
        vehicleSeatCountingDataAggregateList.add(new VehicleSeatCountingDataAggregate());
        map.put(5, (List<T>) vehicleSeatCountingDataAggregateList);
    }

    @Scheduled(fixedRate=5000)
    public  void sendMessage() {
        Random r = new Random();
        int low = 1;
        int high = 5;
        int result = r.nextInt(high - low) + low;
        String tenant = null;
        for (T item : map.get(result)) {
            if (item instanceof TrainElementBluetoothCountingData) {
                tenant = String.valueOf(((TrainElementBluetoothCountingData) item).getTenantId());
            } else if (item instanceof VehicleBluetoothCountingData) {
                tenant = String.valueOf(((VehicleBluetoothCountingData) item).getTenantId());
            } else if (item instanceof VehiclePeopleCountingData) {
                tenant = String.valueOf(((VehiclePeopleCountingData) item).getTenantId());
            } else if (item instanceof VehiclePosition) {
                tenant = String.valueOf(((VehiclePosition) item).getTenantId());
            } else if (item instanceof VehicleSeatCountingDataAggregate) {
                tenant = String.valueOf(((VehicleSeatCountingDataAggregate) item).getTenantId());
            }
        }

        Set<String> userTenant = simpUserRegistry.getUsers().stream().map(u ->((User)((UsernamePasswordAuthenticationToken) u.getPrincipal()).getDetails()).getTenant()).collect(Collectors.toSet());
        if (tenant != null &&  userTenant.contains(tenant))
            simpMessagingTemplate.convertAndSend("/topic/"+tenant+"/message",
                    map.get(result));

    }


    @MessageMapping("/user-info")
    @SendToUser("/topic/user-info")
    public ProvaResponse getUser(Prova user) {
        return new ProvaResponse("Hi " + user.getName());
    }


}

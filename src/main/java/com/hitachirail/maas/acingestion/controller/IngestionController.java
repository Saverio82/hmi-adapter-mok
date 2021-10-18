package com.hitachirail.maas.acingestion.controller;

import com.hitachirail.maas.acingestion.beans.*;
import com.hitachirail.maas.acingestion.service.IngestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingestion-service")
@Slf4j
public class IngestionController {

    private IngestionService ingestionService;

    @Autowired
    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/seat-counting-data")
    public ResponseEntity<String> insertSeatCountingDataAggregate(@RequestBody DataRequestContainer<SeatCountingDataAggregate> dataRequest) {
        log.info("Call 'SeatCountingDataAggregate' post request. list count: {}", dataRequest.getData().size());

        if(dataRequest.getData().isEmpty()) {
            return new ResponseEntity<>("list is empty", HttpStatus.BAD_REQUEST);
        }

        //this.ingestionService.publishSeatCountingDataAggregateOnInternalKafkaQueues(dataRequest.getData());

        log.debug("'/ingestion-service/seat-counting-data' endpoint called successfully");

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/people-counting-data")
    public ResponseEntity<String> insertPeopleCountingData(@RequestBody DataRequestContainer<PeopleCountingData> dataRequest){
        List<PeopleCountingData> peopleCountingData = dataRequest.getData();

        log.info("Call 'PeopleCountingData' post request. list count: {}", peopleCountingData);

        if(peopleCountingData.isEmpty()) {
            return new ResponseEntity<>("list is empty", HttpStatus.BAD_REQUEST);
        }

        //this.ingestionService.publishPeopleCountingDataOnInternalKafkaQueues(peopleCountingData);

        log.debug("'/ingestion-service/people-counting-data' endpoint called successfully");

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/bluetooth-counting-data")
    public ResponseEntity<String> insertBluetoothCountingData(@RequestBody DataRequestContainer<BluetoothCountingData> dataRequest){
        log.info("Call 'BluetoothCountingData' post request. list count: {}", dataRequest.getData().size());

        if(dataRequest.getData().isEmpty()){
            return new ResponseEntity<>("List is empty", HttpStatus.BAD_REQUEST);
        }

        //this.ingestionService.publishBluetoothCountingDataOnInternalKafkaQueues(dataRequest.getData());

        log.debug("'/ingestion-service/bluetooth-counting-data' endpoint called successfully");

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/position")
    public ResponseEntity<String> insertPosition(@RequestBody DataRequestContainer<Position> dataRequest){
        log.info("Call 'Position' post request. list count: {}", dataRequest.getData().size());

        if(dataRequest.getData().isEmpty()){
            return new ResponseEntity<>("List is empty", HttpStatus.BAD_REQUEST);
        }

        //this.ingestionService.publishPositionOnInternalKafkaQueues(dataRequest.getData());

        log.debug("'/ingestion-service/position' endpoint called successfully");

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/station-congestion")
    public ResponseEntity<String> insertStationCongestion(@RequestBody DataRequestContainer<StationCongestion> dataRequest){
        log.info("Call 'Position' post request. list count: {}", dataRequest.getData().size());

        if(dataRequest.getData().isEmpty()){
            return new ResponseEntity<>("List is empty", HttpStatus.BAD_REQUEST);
        }

        //this.ingestionService.publishStationCongestionOnInternalKafkaQueues(dataRequest.getData());

        log.debug("'/ingestion-service/station-congestion' endpoint called successfully");

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}

package com.hitachirail.maas.acingestion.controller;

import com.hitachirail.maas.acingestion.beans.*;
import com.hitachirail.maas.acingestion.controller.beans.DataRequestContainer;
import com.hitachirail.maas.acingestion.service.IngestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingestion-service")
@Slf4j
public class IngestionController {

    private static final String LIST_EMPTY_ERROR_MSG = "'data' property cannot be null or empty";

    private IngestionService ingestionService;

    @Autowired
    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/seat-counting-data")
    public ResponseEntity<String> insertSeatCountingDataAggregate(@RequestBody DataRequestContainer<SeatCountingDataAggregate> dataRequestContainer) {
        if(dataRequestContainer.getData() == null || dataRequestContainer.getData().isEmpty()) {
            return new ResponseEntity<>(LIST_EMPTY_ERROR_MSG, HttpStatus.BAD_REQUEST);
        }

        log.info("Call 'SeatCountingDataAggregate' post request. list count: {}", dataRequestContainer.getData().size());

        this.ingestionService.publishSeatCountingDataAggregateOnInternalKafkaQueues(dataRequestContainer.getData());

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/people-counting-data")
    public ResponseEntity<String> insertPeopleCountingData(@RequestBody DataRequestContainer<PeopleCountingData> dataRequestContainer){
        if(dataRequestContainer.getData() == null || dataRequestContainer.getData().isEmpty()) {
            return new ResponseEntity<>(LIST_EMPTY_ERROR_MSG, HttpStatus.BAD_REQUEST);
        }

        log.info("Call 'PeopleCountingData' post request. list count: {}", dataRequestContainer.getData().size());

        this.ingestionService.publishPeopleCountingDataOnInternalKafkaQueues(dataRequestContainer.getData());

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/bluetooth-counting-data")
    public ResponseEntity<String> insertBluetoothCountingData(@RequestBody DataRequestContainer<BluetoothCountingData> dataRequestContainer){
        if(dataRequestContainer.getData() == null || dataRequestContainer.getData().isEmpty()) {
            return new ResponseEntity<>(LIST_EMPTY_ERROR_MSG, HttpStatus.BAD_REQUEST);
        }

        log.info("Call 'BluetoothCountingData' post request. list count: {}", dataRequestContainer.getData().size());

        this.ingestionService.publishBluetoothCountingDataOnInternalKafkaQueues(dataRequestContainer.getData());

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/position")
    public ResponseEntity<String> insertPosition(@RequestBody DataRequestContainer<Position> dataRequestContainer){
        if(dataRequestContainer.getData() == null || dataRequestContainer.getData().isEmpty()) {
            return new ResponseEntity<>(LIST_EMPTY_ERROR_MSG, HttpStatus.BAD_REQUEST);
        }

        log.info("Call 'Position' post request. list count: {}", dataRequestContainer.getData().size());

        this.ingestionService.publishPositionOnInternalKafkaQueues(dataRequestContainer.getData());

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/station-congestion")
    public ResponseEntity<String> insertStationCongestion(@RequestBody DataRequestContainer<StationCongestion> dataRequestContainer){
        if(dataRequestContainer.getData() == null || dataRequestContainer.getData().isEmpty()) {
            return new ResponseEntity<>(LIST_EMPTY_ERROR_MSG, HttpStatus.BAD_REQUEST);
        }

        log.info("Call 'Station Congestion' post request. list count: {}", dataRequestContainer.getData().size());

        this.ingestionService.publishStationCongestionOnInternalKafkaQueues(dataRequestContainer.getData());

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}

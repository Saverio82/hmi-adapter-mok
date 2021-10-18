package com.hitachirail.maas.acingestion.service;

import com.hitachirail.maas.acingestion.beans.*;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimRule;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class IngestionServiceImpl  implements IngestionService {

    private ProducerService<Position> positionProducerService;
    private ProducerService<BluetoothCountingData> bluetoothCountingDataProducerService;
    private ProducerService<PeopleCountingData> peopleCountingDataProducerService;
    private ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducerService;
    private ProducerService<StationCongestion> stationCongestionProducerService;

    @Autowired
    public IngestionServiceImpl(ProducerService<Position> positionProducerService,
                                ProducerService<BluetoothCountingData> bluetoothCountingDataProducerService,
                                ProducerService<PeopleCountingData> peopleCountingDataProducerService,
                                ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducerService,
                                ProducerService<StationCongestion> stationCongestionProducerService) {
        this.positionProducerService = positionProducerService;
        this.bluetoothCountingDataProducerService = bluetoothCountingDataProducerService;
        this.peopleCountingDataProducerService = peopleCountingDataProducerService;
        this.seatCountingDataAggregateProducerService = seatCountingDataAggregateProducerService;
        this.stationCongestionProducerService = stationCongestionProducerService;
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = "scope", equalToValue = "eav", required = true)
    @Override
    public void publishPositionOnInternalKafkaQueues(List<Position> positions) {
        this.positionProducerService.publishListOnKafkaBulkTopic(positions);
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = "scope", equalToValue = "eav", required = true)
    @Override
    public void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingData> bluetoothCountingData) {
        this.bluetoothCountingDataProducerService.publishListOnKafkaBulkTopic(bluetoothCountingData);
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = "scope", equalToValue = "eav", required = true)
    @Override
    public void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingData> peopleCountingData) {
        this.peopleCountingDataProducerService.publishListOnKafkaBulkTopic(peopleCountingData);
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = "scope", equalToValue = "eav", required = true)
    @Override
    public void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregate> seatCountingDataAggregate) {
        this.seatCountingDataAggregateProducerService.publishListOnKafkaBulkTopic(seatCountingDataAggregate);
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = "scope", equalToValue = "eav", required = true)
    @Override
    public void publishStationCongestionOnInternalKafkaQueues(List<StationCongestion> stationCongestionList) {
        this.stationCongestionProducerService.publishListOnKafkaBulkTopic(stationCongestionList);
    }
}

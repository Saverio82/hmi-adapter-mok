package com.hitachirail.maas.acingestion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.*;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimRule;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MaasProducerUser
@Slf4j
public class IngestionServiceImpl  implements IngestionService {

    private static final String CLAIM_PROPERTY = "scope";
    private static final String SCOPE_VALUE = "ac-ingestion";

    @MaasProducer(
            kafkaTopic = "${kafka.bluetooth.counting.data.bulk.topic}"
    )
    private MaasProducerComponent bluetoothCountingDataProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}"
    )
    private MaasProducerComponent peopleCountingDataProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.position.bulk.topic}"
    )
    private MaasProducerComponent positionProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.bulk.topic}"
    )
    private MaasProducerComponent seatCountingDataAggregateProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}"
    )
    private MaasProducerComponent stationCongestionProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public IngestionServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishPositionOnInternalKafkaQueues(List<Position> positions) throws JsonProcessingException {
        this.positionProducer.publish(this.objectMapper.writeValueAsString(positions));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingData> bluetoothCountingData) throws JsonProcessingException {
        this.bluetoothCountingDataProducer.publish(this.objectMapper.writeValueAsString(bluetoothCountingData));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingData> peopleCountingData) throws JsonProcessingException {
        this.peopleCountingDataProducer.publish(this.objectMapper.writeValueAsString(peopleCountingData));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregate> seatCountingDataAggregate) throws JsonProcessingException {
        this.seatCountingDataAggregateProducer.publish(this.objectMapper.writeValueAsString(seatCountingDataAggregate));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishStationCongestionOnInternalKafkaQueues(List<StationCongestion> stationCongestionList) throws JsonProcessingException {
        this.stationCongestionProducer.publish(this.objectMapper.writeValueAsString(stationCongestionList));
    }
}

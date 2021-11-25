package com.hitachirail.maas.acingestion.service;

import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachirail.maas.acingestion.beans.*;
import com.hitachirail.maas.acingestion.businessentity.*;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimRule;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@MaasProducerUser
@Slf4j
public class IngestionServiceImpl  implements IngestionService {

    private static final String CLAIM_PROPERTY = "scope";
    private static final String SCOPE_VALUE = "ac-ingestion";

    private ProducerService<BusinessPosition> positionProducer;
    private ProducerService<BusinessBluetoothCountingData> bluetoothCountingDataProducer;
    private ProducerService<BusinessPeopleCountingData> peopleCountingDataProducer;
    private ProducerService<BusinessStationCongestion> stationCongestionProducer;
    private ProducerService<BusinessSeatCountingDataAggregate> seatCountingDataAggregateProducer;

    @Autowired
    public IngestionServiceImpl(ProducerService<BusinessPosition> positionProducer,
                                ProducerService<BusinessBluetoothCountingData> bluetoothCountingDataProducer,
                                ProducerService<BusinessPeopleCountingData> peopleCountingDataProducer,
                                ProducerService<BusinessStationCongestion> stationCongestionProducer,
                                ProducerService<BusinessSeatCountingDataAggregate> seatCountingDataAggregateProducer) {
        this.positionProducer = positionProducer;
        this.bluetoothCountingDataProducer = bluetoothCountingDataProducer;
        this.peopleCountingDataProducer = peopleCountingDataProducer;
        this.stationCongestionProducer = stationCongestionProducer;
        this.seatCountingDataAggregateProducer = seatCountingDataAggregateProducer;
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishPositionOnInternalKafkaQueues(List<Position> positions) throws Exception {
        this.positionProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(positions, BusinessPosition.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingData> bluetoothCountingData) throws Exception {
        this.bluetoothCountingDataProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(bluetoothCountingData, BusinessBluetoothCountingData.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingData> peopleCountingData) throws Exception {
        this.peopleCountingDataProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(peopleCountingData, BusinessPeopleCountingData.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregate> seatCountingDataAggregateData) throws Exception {
        this.seatCountingDataAggregateProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(seatCountingDataAggregateData,
                BusinessSeatCountingDataAggregate.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishStationCongestionOnInternalKafkaQueues(List<StationCongestion> stationCongestionData) throws Exception {
        this.stationCongestionProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(stationCongestionData, BusinessStationCongestion.class));
    }

    private <S, T> List<T> convertListToBusinessList(List<S> sourceList, Class<T> targetClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> listToReturn = new ArrayList<>();

        for(S sourceObj : sourceList) {
            T targetObj = targetClass.getConstructor().newInstance();
            BeanUtils.copyProperties(sourceObj, targetObj);
            listToReturn.add(targetObj);
        }

        return listToReturn;
    }
}

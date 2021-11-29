package com.hitachirail.maas.acingestion.service;

import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachirail.maas.acingestion.beans.*;
import com.hitachirail.maas.acingestion.dto.*;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimRule;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class IngestionServiceImpl  implements IngestionService {

    private static final String CLAIM_PROPERTY = "scope";
    private static final String SCOPE_VALUE = "ac-ingestion";

    private ProducerService<Position> positionProducer;
    private ProducerService<BluetoothCountingData> bluetoothCountingDataProducer;
    private ProducerService<PeopleCountingData> peopleCountingDataProducer;
    private ProducerService<StationCongestion> stationCongestionProducer;
    private ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducer;

    @Autowired
    public IngestionServiceImpl(ProducerService<Position> positionProducer,
                                ProducerService<BluetoothCountingData> bluetoothCountingDataProducer,
                                ProducerService<PeopleCountingData> peopleCountingDataProducer,
                                ProducerService<StationCongestion> stationCongestionProducer,
                                ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducer) {
        this.positionProducer = positionProducer;
        this.bluetoothCountingDataProducer = bluetoothCountingDataProducer;
        this.peopleCountingDataProducer = peopleCountingDataProducer;
        this.stationCongestionProducer = stationCongestionProducer;
        this.seatCountingDataAggregateProducer = seatCountingDataAggregateProducer;
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishPositionOnInternalKafkaQueues(List<PositionDTO> positionDTOS) throws Exception {
        this.positionProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(positionDTOS, Position.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingDataDTO> bluetoothCountingDatumDTOS) throws Exception {
        this.bluetoothCountingDataProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(bluetoothCountingDatumDTOS, BluetoothCountingData.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingDataDTO> peopleCountingDatumDTOS) throws Exception {
        this.peopleCountingDataProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(peopleCountingDatumDTOS, PeopleCountingData.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregateDTO> seatCountingDataAggregateDTOData) throws Exception {
        this.seatCountingDataAggregateProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(seatCountingDataAggregateDTOData,
                SeatCountingDataAggregate.class));
    }

    @AuthClaimVerify
    @AuthClaimRule(claimProperty = CLAIM_PROPERTY, equalToValue = SCOPE_VALUE, required = true)
    @Override
    public void publishStationCongestionOnInternalKafkaQueues(List<StationCongestionDTO> stationCongestionDTOData) throws Exception {
        this.stationCongestionProducer.publishListOnKafkaBulkTopic(convertListToBusinessList(stationCongestionDTOData, StationCongestion.class));
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

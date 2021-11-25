package com.hitachirail.maas.acingestion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hitachirail.maas.acingestion.beans.*;

import java.util.List;

public interface IngestionService {
    void publishPositionOnInternalKafkaQueues(List<Position> positionList) throws Exception;
    void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingData> bluetoothCountingDataList) throws Exception;
    void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingData> peopleCountingDataList) throws Exception;
    void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregate> seatCountingDataAggregateList) throws Exception;
    void publishStationCongestionOnInternalKafkaQueues(List<StationCongestion> stationCongestionList) throws Exception;
}

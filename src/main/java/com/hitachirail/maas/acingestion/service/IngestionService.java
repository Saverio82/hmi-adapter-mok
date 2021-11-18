package com.hitachirail.maas.acingestion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hitachirail.maas.acingestion.beans.*;

import java.util.List;

public interface IngestionService {
    void publishPositionOnInternalKafkaQueues(List<Position> positionList) throws JsonProcessingException;
    void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingData> bluetoothCountingDataList) throws JsonProcessingException;
    void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingData> peopleCountingDataList) throws JsonProcessingException;
    void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregate> seatCountingDataAggregateList) throws JsonProcessingException;
    void publishStationCongestionOnInternalKafkaQueues(List<StationCongestion> stationCongestionList) throws JsonProcessingException;
}

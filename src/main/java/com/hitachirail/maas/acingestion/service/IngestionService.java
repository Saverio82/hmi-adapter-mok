package com.hitachirail.maas.acingestion.service;

import com.hitachirail.maas.acingestion.beans.*;

import java.util.List;

public interface IngestionService {
    void publishPositionOnInternalKafkaQueues(List<Position> positionList);
    void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingData> bluetoothCountingDataList);
    void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingData> peopleCountingDataList);
    void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregate> seatCountingDataAggregateList);
    void publishStationCongestionOnInternalKafkaQueues(List<StationCongestion> stationCongestionList);
}

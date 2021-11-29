package com.hitachirail.maas.acingestion.service;

import com.hitachirail.maas.acingestion.dto.*;

import java.util.List;

public interface IngestionService {
    void publishPositionOnInternalKafkaQueues(List<PositionDTO> positionDTOList) throws Exception;
    void publishBluetoothCountingDataOnInternalKafkaQueues(List<BluetoothCountingDataDTO> bluetoothCountingDataDTOList) throws Exception;
    void publishPeopleCountingDataOnInternalKafkaQueues(List<PeopleCountingDataDTO> peopleCountingDataDTOList) throws Exception;
    void publishSeatCountingDataAggregateOnInternalKafkaQueues(List<SeatCountingDataAggregateDTO> seatCountingDataAggregateDTOList) throws Exception;
    void publishStationCongestionOnInternalKafkaQueues(List<StationCongestionDTO> stationCongestionDTOList) throws Exception;
}

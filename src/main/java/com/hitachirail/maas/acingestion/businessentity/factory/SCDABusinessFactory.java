package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.businessentity.SeatCountingDataDetails;
import com.hitachirail.maas.acingestion.businessentity.SeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.businessentity.TrainElementSeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.businessentity.VehicleSeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.dto.SeatCountingDataAggregateDTO;
import com.hitachirail.maas.acingestion.dto.SeatCountingDataDetailsDTO;
import com.hitachirail.maas.acingestion.enums.ElementTypeEnum;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;

import java.util.ArrayList;
import java.util.List;

public class SCDABusinessFactory {
    public static BusinessObjectWrapper<SeatCountingDataAggregate> createSCDABusiness(SeatCountingDataAggregateDTO data, Long tenantId) {
        switch(data.getType()){
            case 0:
                return new BusinessObjectWrapper<SeatCountingDataAggregate>(ElementTypeEnum.TRAIN_ELEMENT.name(), buildTrainElementSeatCountingDataAggregate(data, tenantId));
            case 1:
                return new BusinessObjectWrapper<SeatCountingDataAggregate>(ElementTypeEnum.VEHICLE.name(), buildVehicleSeatCountingDataAggregate(data, tenantId));
            default: return null;

        }
    }

    private static VehicleSeatCountingDataAggregate buildVehicleSeatCountingDataAggregate(SeatCountingDataAggregateDTO data, Long tenantId) {
        VehicleSeatCountingDataAggregate vehicleSeatCountingDataAggregate = new VehicleSeatCountingDataAggregate().builder()
                .vehicleId(data.getVehicleId())
                .tenantId(tenantId)
                .messageId(data.getMessageId())
                .serviceJourneyId(data.getTripId())
                .seatOccupancy(data.getSeatingOccupancy())
                .sourceSystemId(data.getSourceSystemId())
                .diagnosticStatus(data.getDiagnosticStatus())
                .sysTimestamp(data.getSysTimestamp())
                .build();

        List<SeatCountingDataDetailsDTO> dataDetailsDTOS = data.getSeatCountingDataDetailsDTOList();
        List<SeatCountingDataDetails> dataDetails = new ArrayList<>();
        for(SeatCountingDataDetailsDTO dataDetailsDTO : dataDetailsDTOS){
            SeatCountingDataDetails dataDetail = SeatCountingDataDetails.builder()
                    .sensorId(dataDetailsDTO.getSensorId())
                    .sensorType(dataDetailsDTO.getSensorType())
                    .status(dataDetailsDTO.getStatus())
                    .seatTimestamp(dataDetailsDTO.getSeatTimestamp())
                    .build();
            dataDetails.add(dataDetail);
        }

        vehicleSeatCountingDataAggregate.setSeatCountingDataDetailsList(dataDetails);
        return vehicleSeatCountingDataAggregate;
    }

    private static TrainElementSeatCountingDataAggregate buildTrainElementSeatCountingDataAggregate(SeatCountingDataAggregateDTO data, Long tenantId){
        TrainElementSeatCountingDataAggregate trainElementSeatCountingDataAggregate =new TrainElementSeatCountingDataAggregate().builder().build();

        List<SeatCountingDataDetailsDTO> dataDetailsDTOS = data.getSeatCountingDataDetailsDTOList();
        List<SeatCountingDataDetails> dataDetails = new ArrayList<>();
        for(SeatCountingDataDetailsDTO dataDetailsDTO : dataDetailsDTOS){
            SeatCountingDataDetails dataDetail = SeatCountingDataDetails.builder()
                    .sensorId(dataDetailsDTO.getSensorId())
                    .sensorType(dataDetailsDTO.getSensorType())
                    .status(dataDetailsDTO.getStatus())
                    .seatTimestamp(dataDetailsDTO.getSeatTimestamp())
                    .build();
            dataDetails.add(dataDetail);
        }
        trainElementSeatCountingDataAggregate.setSeatCountingDataDetailsList(dataDetails);
        return trainElementSeatCountingDataAggregate;

    }
}

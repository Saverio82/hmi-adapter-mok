package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.businessentity.Position;
import com.hitachirail.maas.acingestion.businessentity.TrainElementPosition;
import com.hitachirail.maas.acingestion.businessentity.VehiclePosition;
import com.hitachirail.maas.acingestion.dto.PositionDTO;
import com.hitachirail.maas.acingestion.enums.ElementTypeEnum;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;

public class PositionBusinessFactory {

    public static BusinessObjectWrapper<Position> createPositionBusiness(PositionDTO position, Long tenantId){
        switch (position.getType()){
            case 0:
                BusinessObjectWrapper wrapper = new BusinessObjectWrapper(ElementTypeEnum.TRAIN_ELEMENT.name(), buildTrainElementPositionBuiness(position, tenantId));
                return wrapper;
            case 1:
                return new BusinessObjectWrapper<Position>(ElementTypeEnum.VEHICLE.name(), buildVehiclePositionBuiness(position, tenantId));
            default: return null;
        }

    }

    private static VehiclePosition buildVehiclePositionBuiness(PositionDTO position, Long tenantId){
        return new VehiclePosition().builder().
               tenantId(tenantId).
                vehicleId(position.getVehicleId()).
                messageId(position.getMessageId()).
                serviceJourneyId(position.getTripId()).
                diagnosticStatus(position.getDiagnosticStatus()).
                sourceSystemId(position.getSourceSystemId()).
                latitude(position.getLatitude()).
                longitude(position.getLongitude()).
                sysTimestamp(position.getSysTimestamp())
                .build();

    }

    private static TrainElementPosition buildTrainElementPositionBuiness(PositionDTO position, Long tenantId){
        return new TrainElementPosition().builder().
                tenantId(tenantId).
                trainElementId(position.getVehicleId()).
                trainId(position.getParentVehicleId()).
                messageId(position.getMessageId()).
                serviceJourneyId(position.getTripId()).
                diagnosticStatus(position.getDiagnosticStatus()).
                sourceSystemId(position.getSourceSystemId()).
                latitude(position.getLatitude()).
                longitude(position.getLongitude()).
                sysTimestamp(position.getSysTimestamp())
                .build();
    }

}

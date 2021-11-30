package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.businessentity.PositionBusiness;
import com.hitachirail.maas.acingestion.businessentity.TrainElementPositionBusiness;
import com.hitachirail.maas.acingestion.businessentity.VehiclePositionBusiness;
import com.hitachirail.maas.acingestion.beans.Position;
import com.hitachirail.maas.acingestion.enums.ElementTypeEnum;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;

public class PositionBusinessFactory {

    public static BusinessObjectWrapper<PositionBusiness> createPositionBusiness(Position position, Long tenantId){
        switch (position.getType()){
            case 0:
                BusinessObjectWrapper wrapper = new BusinessObjectWrapper(ElementTypeEnum.TRAIN_ELEMENT.name(), buildTrainElementPositionBuiness(position, tenantId));
                return wrapper;
            case 1:
                return new BusinessObjectWrapper<PositionBusiness>(ElementTypeEnum.VEHICLE.name(), buildVehiclePositionBuiness(position, tenantId));
            default: return null;
        }

    }

    private static VehiclePositionBusiness  buildVehiclePositionBuiness(Position position, Long tenantId){
        return new VehiclePositionBusiness().builder().
               tenantId(tenantId).
                vehicleId(position.getVehicleId()).
                messageId(position.getMessageId()).
                serviceJourneyId(position.getSourceSystemId()).
                diagnosticStatus(position.getDiagnosticStatus()).
                sourceSystemId(position.getSourceSystemId()).
                latitude(position.getLatitude()).
                longitude(position.getLongitude()).
                sysTimestamp(position.getSysTimestamp())
                .build();

    }

    private static TrainElementPositionBusiness  buildTrainElementPositionBuiness(Position position, Long tenantId){
        return new TrainElementPositionBusiness().builder().
                tenantId(tenantId).
                trainElementId(position.getVehicleId()).
                trainId(position.getParentVehicleId()).
                messageId(position.getMessageId()).
                serviceJourneyId(position.getSourceSystemId()).
                diagnosticStatus(position.getDiagnosticStatus()).
                sourceSystemId(position.getSourceSystemId()).
                latitude(position.getLatitude()).
                longitude(position.getLongitude()).
                sysTimestamp(position.getSysTimestamp())
                .build();
    }

}

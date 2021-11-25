package com.hitachirail.maas.acingestion.dto.business.factory;

import com.hitachirail.maas.acingestion.businessentity.BusinessPosition;
import com.hitachirail.maas.acingestion.businessentity.TrainElementPositionBusiness;
import com.hitachirail.maas.acingestion.businessentity.VehiclePositionBusiness;
import com.hitachirail.maas.acingestion.beans.Position;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;

public class PositionBusinessFactory {

    public static BusinessObjectWrapper<BusinessPosition> createPositionBusiness(Position position, Long tenantId){
        switch (position.getType()){
            case 0:
                return new BusinessObjectWrapper<BusinessPosition>(0, buildVehiclePositionBuiness(position, tenantId));
            case 1:
                return new BusinessObjectWrapper<BusinessPosition>(1, buildTrainElementPositionBuiness(position, tenantId));
            default: return null;
        }

    }

    private static VehiclePositionBusiness  buildVehiclePositionBuiness(Position position, Long tenantId){
        return new VehiclePositionBusiness(position.getVehicleId(), tenantId,
                position.getTripId(), position.getSourceSystemId(), position.getMessageId(),
                position.getDiagnosticStatus(), position.getLatitude(), position.getLongitude(), position.getSysTimestamp() );
    }

    private static TrainElementPositionBusiness  buildTrainElementPositionBuiness(Position position, Long tenantId){
        return new TrainElementPositionBusiness(position.getVehicleId(), tenantId, position.getParentVehicleId(),
                position.getTripId(), position.getSourceSystemId(), position.getMessageId(),
                position.getDiagnosticStatus(), position.getLatitude(), position.getLongitude(), position.getSysTimestamp() );
    }

}

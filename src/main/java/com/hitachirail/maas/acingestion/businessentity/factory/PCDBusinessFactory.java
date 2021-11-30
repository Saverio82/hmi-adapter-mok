package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.beans.PeopleCountingData;
import com.hitachirail.maas.acingestion.businessentity.PCDBusiness;
import com.hitachirail.maas.acingestion.businessentity.TrainElementPCDBusiness;
import com.hitachirail.maas.acingestion.businessentity.VehiclePCDBusiness;
import com.hitachirail.maas.acingestion.enums.ElementTypeEnum;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;

public class PCDBusinessFactory {

    public static BusinessObjectWrapper<PCDBusiness> createPCDBusiness(PeopleCountingData pcd, Long tenantId){
        switch (pcd.getType()){
            case 0:
                return new BusinessObjectWrapper(ElementTypeEnum.TRAIN_ELEMENT.name(), buildTrainElementPCDBuiness(pcd, tenantId));

            case 1:
                return new BusinessObjectWrapper<PCDBusiness>(ElementTypeEnum.VEHICLE.name(), buildVehiclePCDBuiness(pcd, tenantId));
            default: return null;
        }

    }

    private static VehiclePCDBusiness buildVehiclePCDBuiness(PeopleCountingData pcd, Long tenantId){
        return new VehiclePCDBusiness().builder().
                tenantId(tenantId).
                vehicleId(pcd.getVehicleId()).
                messageId(pcd.getMessageId()).
                serviceJourneyId(pcd.getSourceSystemId()).
                diagnosticStatus(pcd.getDiagnosticStatus()).
                sourceSystemId(pcd.getSourceSystemId()).
                latitude(pcd.getLatitude()).
                longitude(pcd.getLongitude()).
                peopleIn(pcd.getPeopleIn()).
                peopleOut(pcd.getPeopleOut()).
                peopleCount(pcd.getPeopleCount()).
                sysTimestamp(pcd.getSysTimestamp()).
                openTime(pcd.getOpenTime()).
                closeTime(pcd.getCloseTime())
                .build();

    }

    private static TrainElementPCDBusiness  buildTrainElementPCDBuiness(PeopleCountingData pcd, Long tenantId){
        return new TrainElementPCDBusiness().builder().
                tenantId(tenantId).
                trainElementId(pcd.getVehicleId()).
                trainId(pcd.getParentVehicleId()).
                messageId(pcd.getMessageId()).
                serviceJourneyId(pcd.getSourceSystemId()).
                diagnosticStatus(pcd.getDiagnosticStatus()).
                sourceSystemId(pcd.getSourceSystemId()).
                latitude(pcd.getLatitude()).
                longitude(pcd.getLongitude()).
                peopleIn(pcd.getPeopleIn()).
                peopleOut(pcd.getPeopleOut()).
                peopleCount(pcd.getPeopleCount()).
                sysTimestamp(pcd.getSysTimestamp()).
                openTime(pcd.getOpenTime()).
                closeTime(pcd.getCloseTime())
                .build();
    }
}

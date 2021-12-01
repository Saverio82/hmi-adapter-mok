package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.businessentity.PeopleCountingData;
import com.hitachirail.maas.acingestion.businessentity.TrainElementPeopleCountingData;
import com.hitachirail.maas.acingestion.businessentity.VehiclePeopleCountingData;
import com.hitachirail.maas.acingestion.dto.PeopleCountingDataDTO;
import com.hitachirail.maas.acingestion.enums.ElementTypeEnum;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;

public class PCDBusinessFactory {

    public static BusinessObjectWrapper<PeopleCountingData> createPCDBusiness(PeopleCountingDataDTO pcd, Long tenantId){
        switch (pcd.getType()){
            case 0:
                return new BusinessObjectWrapper(ElementTypeEnum.TRAIN_ELEMENT.name(), buildTrainElementPCDBuiness(pcd, tenantId));

            case 1:
                return new BusinessObjectWrapper<PeopleCountingData>(ElementTypeEnum.VEHICLE.name(), buildVehiclePCDBuiness(pcd, tenantId));
            default: return null;
        }

    }

    private static VehiclePeopleCountingData buildVehiclePCDBuiness(PeopleCountingDataDTO pcd, Long tenantId){
        return new VehiclePeopleCountingData().builder().
                tenantId(tenantId).
                vehicleId(pcd.getVehicleId()).
                messageId(pcd.getMessageId()).
                serviceJourneyId(pcd.getTripId()).
                diagnosticStatus(pcd.getDiagnosticStatus()).
                sourceSystemId(pcd.getSourceSystemId()).
                latitude(pcd.getLatitude()).
                longitude(pcd.getLongitude()).
                peopleIn(pcd.getPeopleIn()).
                peopleOut(pcd.getPeopleOut()).
                peopleCount(pcd.getPeopleCount()).
                sysTimestamp(pcd.getSysTimestamp()).
                openTime(pcd.getOpenTime()).
                closeTime(pcd.getCloseTime()).
                scheduledStopPointId(pcd.getStopId())
                .build();

    }

    private static TrainElementPeopleCountingData buildTrainElementPCDBuiness(PeopleCountingDataDTO pcd, Long tenantId){
        return new TrainElementPeopleCountingData().builder().
                tenantId(tenantId).
                trainElementId(pcd.getVehicleId()).
                trainId(pcd.getParentVehicleId()).
                messageId(pcd.getMessageId()).
                serviceJourneyId(pcd.getTripId()).
                diagnosticStatus(pcd.getDiagnosticStatus()).
                sourceSystemId(pcd.getSourceSystemId()).
                latitude(pcd.getLatitude()).
                longitude(pcd.getLongitude()).
                peopleIn(pcd.getPeopleIn()).
                peopleOut(pcd.getPeopleOut()).
                peopleCount(pcd.getPeopleCount()).
                sysTimestamp(pcd.getSysTimestamp()).
                openTime(pcd.getOpenTime()).
                closeTime(pcd.getCloseTime()).
                scheduledStopPointId(pcd.getStopId())
                .build();
    }
}

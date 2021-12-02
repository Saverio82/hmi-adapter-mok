package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.businessentity.BluetoothCountingData;
import com.hitachirail.maas.acingestion.businessentity.TrainElementBluetoothCountingData;
import com.hitachirail.maas.acingestion.businessentity.VehicleBluetoothCountingData;
import com.hitachirail.maas.acingestion.dto.BluetoothCountingDataDTO;
import com.hitachirail.maas.acingestion.dto.DeviceDTO;
import com.hitachirail.maas.acingestion.enums.ElementTypeEnum;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;


public class BCDBusinessFactory {
    public static BusinessObjectWrapper<BluetoothCountingData> createBCDBusiness(BluetoothCountingDataDTO bcd, DeviceDTO device, Long tenantId){
        switch (bcd.getType()){
            case 0:
                return new BusinessObjectWrapper<BluetoothCountingData>(ElementTypeEnum.TRAIN_ELEMENT.name(), buildTrainElementBCDBusinessList(bcd, device, tenantId));
            case 1:
                return new BusinessObjectWrapper<BluetoothCountingData>(ElementTypeEnum.VEHICLE.name(), buildVehicleBCDBusinessList(bcd, device, tenantId));
            default: return null;

        }
    }

    private static VehicleBluetoothCountingData buildVehicleBCDBusinessList(BluetoothCountingDataDTO bluetoothCountingData, DeviceDTO device, Long tenantId){
        return new VehicleBluetoothCountingData().builder().
                vehicleId(bluetoothCountingData.getVehicleId()).
                tenantId(tenantId).
                messageId(bluetoothCountingData.getMessageId()).
                diagnosticStatus(bluetoothCountingData.getDiagnosticStatus()).
                serviceJourneyId(bluetoothCountingData.getTripId()).
                sysTimestamp(bluetoothCountingData.getSysTimestamp()).
                sourceSystemId(bluetoothCountingData.getSourceSystemId()).
                deviceId(device.getDeviceId()).
                status(device.getStatus()).
                latitude(device.getLatitude()).
                longitude(device.getLongitude()).
                scheduledStopPointId(device.getStopId()).
                bleTs(device.getBleTs())
                .build();
    }

    private static TrainElementBluetoothCountingData buildTrainElementBCDBusinessList(BluetoothCountingDataDTO bluetoothCountingData, DeviceDTO device, Long tenantId){
        return new TrainElementBluetoothCountingData().builder().
                trainElementId(bluetoothCountingData.getVehicleId()).
                trainId(bluetoothCountingData.getParentVehicleId()).
                tenantId(tenantId).
                messageId(bluetoothCountingData.getMessageId()).
                diagnosticStatus(bluetoothCountingData.getDiagnosticStatus()).
                serviceJourneyId(bluetoothCountingData.getTripId()).
                sysTimestamp(bluetoothCountingData.getSysTimestamp()).
                sourceSystemId(bluetoothCountingData.getSourceSystemId()).
                deviceId(device.getDeviceId()).
                status(device.getStatus()).
                latitude(device.getLatitude()).
                longitude(device.getLongitude()).
                scheduledStopPointId(device.getStopId()).
                bleTs(device.getBleTs())
                .build();
    }
}

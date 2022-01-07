package com.hitachirail.maas.hmiadapter.businessentity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehicleSeatCountingDataAggregate extends SeatCountingDataAggregate{

    private String vehicleId;

    @Builder
    public VehicleSeatCountingDataAggregate(String serviceJourneyId, String sourceSystemId, Long tenantId, Integer seatOccupancy,
                                            Long sysTimestamp, String messageId, Integer diagnosticStatus, List<SeatCountingDataDetails> seatCountingDataDetailsList, String vehicleId) {
        super(serviceJourneyId, sourceSystemId, tenantId, seatOccupancy, sysTimestamp, messageId, diagnosticStatus, seatCountingDataDetailsList);
        this.vehicleId = vehicleId;
    }

    public VehicleSeatCountingDataAggregate(){
        super.setTenantId(1L);
        super.setServiceJourneyId("21");
        super.setSourceSystemId("AR");
        super.setMessageId("b5a4004e-cf2f-4845-936e-855d0e3031a5");
        super.setDiagnosticStatus(0);
        super.setSeatOccupancy(231);
        super.setSeatCountingDataDetailsList(new ArrayList<SeatCountingDataDetails>());
        super.setSysTimestamp(112312L);
        this.vehicleId = "34";


    }
}

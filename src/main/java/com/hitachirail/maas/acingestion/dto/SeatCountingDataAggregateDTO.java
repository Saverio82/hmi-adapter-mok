package com.hitachirail.maas.acingestion.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SeatCountingDataAggregateDTO {

    private String parentVehicleId;
    private String vehicleId;
    private String tripId;
    private String sourceSystemId;
    private Integer type;
    private String operator;
    private Integer seatingOccupancy;
    private Long sysTimestamp;
    private String messageId;
    private Integer diagnosticStatus;
    private List<SeatCountingDataDetailsDTO> seatCountingDataDetailsDTOList;

    @JsonCreator
    public SeatCountingDataAggregateDTO(@JsonProperty("parent_vehicle_id") String parentVehicleId,
                                        @JsonProperty(value = "vehicle_id", required = true) String vehicleId,
                                        @JsonProperty("trip_id") String tripId,
                                        @JsonProperty("source_system_id") String sourceSystemId,
                                        @JsonProperty(value = "type", required = true) Integer type,
                                        @JsonProperty(value = "operator", required = true) String operator,
                                        @JsonProperty(value = "seating_occupancy", required = true) Integer seatingOccupancy,
                                        @JsonProperty(value = "sys_ts", required = true) Long sysTimestamp,
                                        @JsonProperty(value = "msg_id", required = true) String messageId,
                                        @JsonProperty(value = "diagnostic_status", required = true) Integer diagnosticStatus,
                                        @JsonProperty(value = "updates", required = true) List<SeatCountingDataDetailsDTO> seatCountingDataDetailsDTOList) {
        this.parentVehicleId = parentVehicleId;
        this.vehicleId = vehicleId;
        this.tripId = tripId;
        this.sourceSystemId = sourceSystemId;
        this.type = type;
        this.operator = operator;
        this.seatingOccupancy = seatingOccupancy;
        this.sysTimestamp = sysTimestamp;
        this.messageId = messageId;
        this.diagnosticStatus = diagnosticStatus;
        this.seatCountingDataDetailsDTOList = seatCountingDataDetailsDTOList;
    }
}

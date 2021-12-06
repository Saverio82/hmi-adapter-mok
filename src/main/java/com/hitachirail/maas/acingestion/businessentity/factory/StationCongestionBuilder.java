package com.hitachirail.maas.acingestion.businessentity.factory;

import com.hitachirail.maas.acingestion.businessentity.StationCongestion;
import com.hitachirail.maas.acingestion.dto.StationCongestionDTO;

public class StationCongestionBuilder {


    public static StationCongestion buildStationCongestionBusiness(StationCongestionDTO sc, Long tenantId) {
        return new StationCongestion().builder().

                scheduledStopPointId(sc.getScheduledStopPointId()).
                parentStopPlaceId(sc.getParentStopId()).
                sourceSystemId(sc.getSourceSystemId()).
                tenantId(tenantId).
                messageId(sc.getMessageId()).
                diagnosticStatus(sc.getDiagnosticStatus()).
                peopleIn(sc.getPeopleIn()).
                peopleOut(sc.getPeopleOut()).
                peopleCount(sc.getPeopleCount()).
                sysTimestamp(sc.getSysTimestamp())
                .build();
    }


}

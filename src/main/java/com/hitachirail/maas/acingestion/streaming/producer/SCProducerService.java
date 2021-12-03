package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.businessentity.StationCongestion;
import com.hitachirail.maas.acingestion.dto.StationCongestionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MaasProducerUser
@Slf4j
public class SCProducerService implements ProducerService<StationCongestionDTO> {


    @MaasProducer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    private ObjectMapper objectMapper;


   // private Gson gson;

    @Autowired
    public SCProducerService(ObjectMapper om) {
        this.objectMapper = om;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<StationCongestionDTO> payload) throws JsonProcessingException {
        log.debug("publish list of {} elements into 'StationCongestion' bulk topic", payload.size());

        this.internalKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }






}

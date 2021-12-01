package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.dto.PositionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MaasProducerUser
@Slf4j
public class PositionProducerService implements ProducerService<PositionDTO> {

    @MaasProducer(
            kafkaTopic = "${kafka.position.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;


    private ObjectMapper objectMapper;

    @Autowired
    public PositionProducerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<PositionDTO> payload) throws JsonProcessingException {
        log.debug("publish list of {} elements into 'Position' bulk topic", payload.size());

        this.internalKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }




}

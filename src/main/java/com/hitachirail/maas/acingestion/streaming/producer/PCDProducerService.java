package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.dto.PeopleCountingDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@MaasProducerUser
@Slf4j
public class PCDProducerService implements ProducerService<PeopleCountingDataDTO>{

    @MaasProducer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.people.counting.data.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public PCDProducerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<PeopleCountingDataDTO> payload) throws JsonProcessingException {
         log.debug("publish list of {} elements into 'PeopleCountingData' bulk topic", payload.size());

         this.internalKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }



}

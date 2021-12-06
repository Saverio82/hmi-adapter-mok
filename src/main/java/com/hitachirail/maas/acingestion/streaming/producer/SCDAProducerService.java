package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;

import com.hitachirail.maas.acingestion.businessentity.SeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.dto.SeatCountingDataAggregateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@MaasProducerUser
@Slf4j
public class SCDAProducerService implements ProducerService<SeatCountingDataAggregateDTO> {

    @MaasProducer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public SCDAProducerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<SeatCountingDataAggregateDTO> payload) throws JsonProcessingException {
        log.debug("publish list of {} elements into 'SeatCountingDataAggregate' bulk topic", payload.size());

        this.internalKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }

}

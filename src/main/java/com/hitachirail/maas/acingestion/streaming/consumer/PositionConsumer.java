package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.BusinessPosition;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class PositionConsumer {

    private ProducerService<BusinessPosition> positionProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public PositionConsumer(ObjectMapper objectMapper, ProducerService<BusinessPosition> positionProducer){
        this.objectMapper = objectMapper;
        this.positionProducer = positionProducer;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","PositionBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.position.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerPCDTopic(List<String> messages) throws JsonProcessingException {
        log.info("consumer messages on 'Position' topic");

        List<BusinessPosition> positionList = new ArrayList<>();

        for(String message : messages)
            positionList.addAll(objectMapper.readValue(message, new TypeReference<List<BusinessPosition>>(){}));

        log.debug("Position list size extracted: {}", positionList.size());

        this.positionProducer.publishListOnKafkaOfficialTopic(positionList);

    }



}

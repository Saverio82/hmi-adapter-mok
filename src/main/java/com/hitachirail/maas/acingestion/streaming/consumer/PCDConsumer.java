package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.BusinessPeopleCountingData;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class PCDConsumer {

    private ObjectMapper objectMapper;
    private ProducerService<BusinessPeopleCountingData> peopleCountingDataProducer;

    @Autowired
    public PCDConsumer(ObjectMapper objectMapper, ProducerService<BusinessPeopleCountingData> peopleCountingDataProducer){
        this.objectMapper = objectMapper;
        this.peopleCountingDataProducer = peopleCountingDataProducer;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","PeopleCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerPCDTopic(List<String> messages) throws JsonProcessingException {
        log.info("consumer messages on 'PeopleCountingData' topic");

        List<BusinessPeopleCountingData> peopleCountingDataList = new ArrayList<>();

        for(String message : messages)
            peopleCountingDataList.addAll(objectMapper.readValue(message, new TypeReference<List<BusinessPeopleCountingData>>(){}));

        log.debug("PeopleCountingData list size extracted: {}", peopleCountingDataList.size());

        this.peopleCountingDataProducer.publishListOnKafkaOfficialTopic(peopleCountingDataList);
    }

}

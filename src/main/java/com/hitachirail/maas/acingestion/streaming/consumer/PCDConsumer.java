package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.PeopleCountingData;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class PCDConsumer {

    @MaasProducer(
            kafkaTopic = "${kafka.people.counting.data.topic}"
    )
    private MaasProducerComponent peopleCountingDataProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public PCDConsumer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","PeopleCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerPCDTopic(List<String> messages) throws JsonProcessingException {
        log.info("consumer messages on 'PeopleCountingData' topic");

        List<PeopleCountingData> peopleCountingDataList = new ArrayList<>();

        for(String message : messages)
            peopleCountingDataList.addAll(objectMapper.readValue(message, new TypeReference<List<PeopleCountingData>>(){}));

        log.debug("PeopleCountingData list size extracted: {}", peopleCountingDataList.size());

        this.peopleCountingDataProducer.publish(objectMapper.writeValueAsString(peopleCountingDataList));

    }

}

package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.PeopleCountingData;
import com.hitachirail.maas.acingestion.businessentity.factory.PCDBusinessFactory;
import com.hitachirail.maas.acingestion.dto.PeopleCountingDataDTO;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectUtils;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;
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
    private ProducerService<BusinessObjectWrapper<PeopleCountingData>> peopleCountingDataProducer;
    private BusinessObjectUtils businessObjectUtils;

    @Autowired
    public PCDConsumer(ObjectMapper objectMapper, ProducerService<BusinessObjectWrapper<PeopleCountingData>> peopleCountingDataProducer, BusinessObjectUtils businessObjectUtils){
        this.objectMapper = objectMapper;
        this.peopleCountingDataProducer = peopleCountingDataProducer;
        this.businessObjectUtils = businessObjectUtils;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","PeopleCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerPCDTopic(List<String> messages) throws JsonProcessingException {
        log.info("consumer messages on 'PeopleCountingData' topic");

        List<PeopleCountingDataDTO> peopleCountingDataList = new ArrayList<>();

        for(String message : messages)
            peopleCountingDataList.addAll(objectMapper.readValue(message, new TypeReference<List<PeopleCountingDataDTO>>(){}));

        log.debug("PeopleCountingData list size extracted: {}", peopleCountingDataList.size());

        for(PeopleCountingDataDTO peopleCountingData : peopleCountingDataList){
            BusinessObjectWrapper<PeopleCountingData> wrapper = PCDBusinessFactory.createPCDBusiness(peopleCountingData, businessObjectUtils.getTenantId(peopleCountingData.getOperator()));
            this.peopleCountingDataProducer.publishOnKafkaOfficialTopic(wrapper);
        }
    }

}

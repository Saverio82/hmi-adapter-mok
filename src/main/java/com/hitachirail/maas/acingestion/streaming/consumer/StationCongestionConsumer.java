package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.BusinessStationCongestion;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class StationCongestionConsumer {

    private ObjectMapper objectMapper;
    private ProducerService<BusinessStationCongestion> stationCongestionProducer;

    @Autowired
    public StationCongestionConsumer(ObjectMapper objectMapper, ProducerService<BusinessStationCongestion> stationCongestionProducer) {
        this.objectMapper = objectMapper;
        this.stationCongestionProducer = stationCongestionProducer;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","StationCongestionBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumeStationCongestionTopic(List<String> messages) throws JsonProcessingException {
        log.info("consume messages on 'StationCongestionBulkTopic' topic.");

        List<BusinessStationCongestion> stationCongestionList = new ArrayList<>();

        for(String message : messages)
            stationCongestionList.addAll(objectMapper.readValue(message, new TypeReference<List<BusinessStationCongestion>>(){}));

        log.debug("StationCongestion list size extracted: {}", stationCongestionList.size());

        //TODO: Entity enrichment

        this.stationCongestionProducer.publishListOnKafkaOfficialTopic(stationCongestionList);
    }

}

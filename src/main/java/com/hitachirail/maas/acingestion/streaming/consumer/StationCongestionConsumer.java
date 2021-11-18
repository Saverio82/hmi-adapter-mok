package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.StationCongestion;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class StationCongestionConsumer {

    @MaasProducer(
            kafkaTopic = "${kafka.station.congestion.topic}"
    )
    private MaasProducerComponent stationCongestionProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public StationCongestionConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","StationCongestionBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumeStationCongestionTopic(List<String> messages) throws JsonProcessingException {
        log.info("consume messages on 'StationCongestionBulkTopic' topic.");

        List<StationCongestion> stationCongestionList = new ArrayList<>();

        for(String message : messages)
            stationCongestionList.addAll(objectMapper.readValue(message, new TypeReference<List<StationCongestion>>(){}));

        log.debug("StationCongestion list size extracted: {}", stationCongestionList.size());

        //TODO: Entity enrichment

        this.stationCongestionProducer.publish(objectMapper.writeValueAsString(stationCongestionList));
    }

}

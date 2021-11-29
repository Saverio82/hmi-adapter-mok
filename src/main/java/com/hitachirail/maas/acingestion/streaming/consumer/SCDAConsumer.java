package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.SeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class SCDAConsumer {

    private ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public SCDAConsumer(ObjectMapper objectMapper, ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducer) {
        this.objectMapper = objectMapper;
        this.seatCountingDataAggregateProducer = seatCountingDataAggregateProducer;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","SeatCountingDataAggregateBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"

    )
    public void consumeSCDATopic(List<String> messages) throws JsonProcessingException {
        log.info("consume messages on 'SeatCountingDataAggregate' topic.");

        List<SeatCountingDataAggregate> seatCountingDataAggregateList = new ArrayList<>();

        for(String message : messages)
            seatCountingDataAggregateList.addAll(objectMapper.readValue(message, new TypeReference<List<SeatCountingDataAggregate>>(){}));

        log.debug("SeatCountingDataAggregate list size extracted: {}", seatCountingDataAggregateList.size());

        //TODO: Entity Enrichment

        this.seatCountingDataAggregateProducer.publishListOnKafkaOfficialTopic(seatCountingDataAggregateList);
    }

}

package com.hitachirail.maas.acingestion.streaming.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.SeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class SCDAConsumer {

    private ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducerService;
    private Gson gson;

    @Autowired
    public SCDAConsumer(ProducerService<SeatCountingDataAggregate> seatCountingDataAggregateProducerService,
                        Gson gson) {
        this.seatCountingDataAggregateProducerService = seatCountingDataAggregateProducerService;
        this.gson = gson;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","SeatCountingDataAggregateBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"

    )
    public void consumeSCDATopic(List<String> messages) {
        log.info("consume messages on 'SeatCountingDataAggregate' topic.");

        List<SeatCountingDataAggregate> seatCountingDataAggregateList = new ArrayList<>();

        Type type = new TypeToken<List<SeatCountingDataAggregate>>() {}.getType();

        for(String message : messages)
            seatCountingDataAggregateList.addAll(gson.fromJson(message, type));

        log.debug("SeatCountingDataAggregate list size extracted: {}", seatCountingDataAggregateList.size());

        //TODO: Entity Enrichment

        this.seatCountingDataAggregateProducerService.publishListOnKafkaOfficialTopic(seatCountingDataAggregateList);
    }

}

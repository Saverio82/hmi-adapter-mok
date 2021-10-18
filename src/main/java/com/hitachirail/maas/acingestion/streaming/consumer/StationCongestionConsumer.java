package com.hitachirail.maas.acingestion.streaming.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.StationCongestion;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class StationCongestionConsumer {

    private ProducerService<StationCongestion> stationCongestionProducerService;
    private Gson gson;

    @Autowired
    public StationCongestionConsumer(ProducerService<StationCongestion> stationCongestionProducerService,
                        Gson gson) {
        this.stationCongestionProducerService = stationCongestionProducerService;
        this.gson = gson;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","StationCongestionBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumeStationCongestionTopic(List<String> messages) {
        log.info("consume messages on 'StationCongestionBulkTopic' topic.");

        List<StationCongestion> stationCongestionList = new ArrayList<>();

        Type type = new TypeToken<List<StationCongestion>>() {}.getType();

        for(String message : messages)
            stationCongestionList.addAll(gson.fromJson(message, type));

        log.debug("StationCongestion list size extracted: {}", stationCongestionList.size());

        //TODO: Entity enrichment

        this.stationCongestionProducerService.publishListOnKafkaOfficialTopic(stationCongestionList);
    }

}

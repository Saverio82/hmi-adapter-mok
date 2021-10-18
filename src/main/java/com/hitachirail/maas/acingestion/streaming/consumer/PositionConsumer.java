package com.hitachirail.maas.acingestion.streaming.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.Position;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class PositionConsumer {

    private ProducerService<Position>  positionProducerService;
    private Gson gson;

    @Autowired
    public PositionConsumer(ProducerService<Position> positionProducerService,
                            Gson gson){
        this.positionProducerService = positionProducerService;
        this.gson = gson;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","PositionBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.position.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerPCDTopic(List<String> messages){
        log.info("consumer messages on 'Position' topic");

        List<Position> positionList = new ArrayList<>();

        Type type = new TypeToken<List<Position>>() {}.getType();

        for(String message : messages)
            positionList.addAll(gson.fromJson(message, type));

        log.debug("Position list size extracted: {}", positionList.size());

        this.positionProducerService.publishListOnKafkaOfficialTopic(positionList);

    }



}

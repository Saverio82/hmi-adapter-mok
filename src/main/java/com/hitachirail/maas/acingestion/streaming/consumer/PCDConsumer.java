package com.hitachirail.maas.acingestion.streaming.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.PeopleCountingData;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class PCDConsumer {

    private ProducerService<PeopleCountingData> peopleCountingDataProducerService;
    private Gson gson;

    @Autowired
    public PCDConsumer(ProducerService<PeopleCountingData> peopleCountingDataProducerService,
                       Gson gson){
        this.peopleCountingDataProducerService = peopleCountingDataProducerService;
        this.gson = gson;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","PeopleCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerPCDTopic(List<String> messages){
        log.info("consumer messages on 'PeopleCountingData' topic");

        List<PeopleCountingData> peopleCountingDataList = new ArrayList<>();

        Type type = new TypeToken<List<PeopleCountingData>>() {}.getType();

        for(String message : messages)
            peopleCountingDataList.addAll(gson.fromJson(message, type));

        log.debug("PeopleCountingData list size extracted: {}", peopleCountingDataList.size());

        this.peopleCountingDataProducerService.publishListOnKafkaOfficialTopic(peopleCountingDataList);

    }

}

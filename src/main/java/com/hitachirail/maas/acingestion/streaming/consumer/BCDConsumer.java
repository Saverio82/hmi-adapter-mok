package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.BluetoothCountingData;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class BCDConsumer {

    private ObjectMapper objectMapper;
    private ProducerService<BluetoothCountingData> bluetoothCountingDataProducer;

    @Autowired
    public BCDConsumer(ObjectMapper objectMapper, ProducerService<BluetoothCountingData> bluetoothCountingDataProducer){
        this.objectMapper = objectMapper;
        this.bluetoothCountingDataProducer = bluetoothCountingDataProducer;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","BluetoothCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.bluetooth.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerBCDTopic(List<String> messages) throws JsonProcessingException {
        log.info("consumer messages on 'BluetoothCountingData' topic");

        List<BluetoothCountingData> bluetoothCountingDataList = new ArrayList<>();

        for(String message : messages)
            bluetoothCountingDataList.addAll(objectMapper.readValue(message, new TypeReference<List<BluetoothCountingData>>(){}));

        log.debug("BluetoothCountingData list size extracted: {}", bluetoothCountingDataList.size());

        this.bluetoothCountingDataProducer.publishListOnKafkaOfficialTopic(bluetoothCountingDataList);
    }

}

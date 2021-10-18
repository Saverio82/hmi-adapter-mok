package com.hitachirail.maas.acingestion.streaming.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.beans.BluetoothCountingData;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class BCDConsumer {

    private ProducerService<BluetoothCountingData> bluetoothCountingDataProducerService;
    private Gson gson;

    @Autowired
    public BCDConsumer(ProducerService<BluetoothCountingData> bluetoothCountingDataProducerService,
                       Gson gson){
        this.bluetoothCountingDataProducerService = bluetoothCountingDataProducerService;
        this.gson = gson;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","BluetoothCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.bluetooth.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerBCDTopic(List<String> messages){
        log.info("consumer messages on 'BluetoothCountingData' topic");

        List<BluetoothCountingData> bluetoothCountingDataList = new ArrayList<>();

        Type type = new TypeToken<List<BluetoothCountingData>>() {}.getType();

        for(String message : messages)
            bluetoothCountingDataList.addAll(gson.fromJson(message, type));

        log.debug("BluetoothCountingData list size extracted: {}", bluetoothCountingDataList.size());

        this.bluetoothCountingDataProducerService.publishListOnKafkaOfficialTopic(bluetoothCountingDataList);

    }

}

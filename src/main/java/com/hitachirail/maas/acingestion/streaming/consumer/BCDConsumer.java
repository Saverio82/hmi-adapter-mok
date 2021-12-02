package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.BluetoothCountingData;
import com.hitachirail.maas.acingestion.businessentity.factory.BCDBusinessFactory;
import com.hitachirail.maas.acingestion.dto.BluetoothCountingDataDTO;
import com.hitachirail.maas.acingestion.dto.DeviceDTO;
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
public class BCDConsumer {

    private ObjectMapper objectMapper;
    private ProducerService<BusinessObjectWrapper<BluetoothCountingData>> bluetoothCountingDataProducer;
    private BusinessObjectUtils businessObjectUtils;

    @Autowired
    public BCDConsumer(ObjectMapper objectMapper, ProducerService<BusinessObjectWrapper<BluetoothCountingData>> bluetoothCountingDataProducer, BusinessObjectUtils businessObjectUtils){
        this.objectMapper = objectMapper;
        this.bluetoothCountingDataProducer = bluetoothCountingDataProducer;
        this.businessObjectUtils = businessObjectUtils;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","BluetoothCountingDataBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.bluetooth.counting.data.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumerBCDTopic(List<String> messages) throws JsonProcessingException {
        log.debug("consumer messages on 'BluetoothCountingData' topic");

        List<BluetoothCountingDataDTO> bluetoothCountingDataList = new ArrayList<>();

        for(String message : messages)
            bluetoothCountingDataList.addAll(objectMapper.readValue(message, new TypeReference<List<BluetoothCountingDataDTO>>(){}));


        for(BluetoothCountingDataDTO bluetoothCountingData : bluetoothCountingDataList) {
            for (DeviceDTO device : bluetoothCountingData.getDeviceDTOS()) {
                BusinessObjectWrapper<BluetoothCountingData> wrapper = BCDBusinessFactory.createBCDBusiness(bluetoothCountingData, device, businessObjectUtils.getTenantId(bluetoothCountingData.getOperator()));
                this.bluetoothCountingDataProducer.publishOnKafkaOfficialTopic(wrapper);
            }

        }

    }


}

package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.businessentity.BluetoothCountingData;
import com.hitachirail.maas.acingestion.dto.BluetoothCountingDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MaasProducerUser
@Slf4j
public class BCDProducerService implements ProducerService <BluetoothCountingDataDTO>{

    @MaasProducer(
            kafkaTopic = "${kafka.bluetooth.counting.data.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.bluetooth.counting.data.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public BCDProducerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<BluetoothCountingDataDTO> payload) throws JsonProcessingException {
        log.debug("publish list of {} elements into 'BluetoothCountingData' bulk topic", payload.size());

        this.internalKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }

}

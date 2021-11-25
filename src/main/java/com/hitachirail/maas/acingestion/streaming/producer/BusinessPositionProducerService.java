package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.businessentity.BusinessPosition;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;

@MaasProducerUser
public class BusinessPositionProducerService implements ProducerService<BusinessObjectWrapper<BusinessPosition>>{

    @MaasProducer(
            kafkaTopic = "${kafka.position.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private ObjectMapper objectMapper;

    @Autowired
    public BusinessPositionProducerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void publishOnKafkaOfficialTopic(BusinessObjectWrapper<BusinessPosition> payload) throws JsonProcessingException {
        this.officialKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }
}

package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.businessentity.StationCongestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@MaasProducerUser
public class BusinessSCProducerService implements ProducerService<StationCongestion>{

    @MaasProducer(
            kafkaTopic = "${kafka.station.congestion.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void publishOnKafkaOfficialTopic(StationCongestion payload) throws
            JsonProcessingException {

        this.officialKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }

    @Override
    public void publishListOnKafkaOfficialTopic(List<StationCongestion> payload) throws JsonProcessingException{
        log.debug("publish list of {} elements into 'StationCongestion' bulk topic", payload.size());

        this.officialKafkaProducer.publish(objectMapper.writeValueAsString(payload));
    }
}

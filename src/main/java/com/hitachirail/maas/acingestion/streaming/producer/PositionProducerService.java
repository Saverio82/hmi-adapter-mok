package com.hitachirail.maas.acingestion.streaming.producer;

import com.google.gson.Gson;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.Position;
import com.hitachirail.maas.acingestion.businessentity.BusinessPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MaasProducerUser
@Slf4j
public class PositionProducerService implements ProducerService<BusinessPosition> {

    @MaasProducer(
            kafkaTopic = "${kafka.position.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.position.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private Gson gson;

    @Autowired
    public PositionProducerService(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<BusinessPosition> payload) {
        log.debug("publish list of {} elements into 'Position' bulk topic", payload.size());

        this.internalKafkaProducer.publish(gson.toJson(payload));
    }

    @Override
    public void publishListOnKafkaOfficialTopic(List<BusinessPosition> payload){
        log.debug("publish list of {} elements into 'Position' bulk topic", payload.size());

        this.officialKafkaProducer.publish(gson.toJson(payload));
    }

}

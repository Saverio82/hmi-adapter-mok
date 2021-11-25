package com.hitachirail.maas.acingestion.streaming.producer;

import com.google.gson.Gson;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.StationCongestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MaasProducerUser
@Slf4j
public class StationCongestionProducerService implements ProducerService<StationCongestion> {

    @MaasProducer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.station.congestion.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private Gson gson;

    @Autowired
    public StationCongestionProducerService(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<StationCongestion> payload) {
        log.debug("publish list of {} elements into 'PeopleCountingData' bulk topic", payload.size());

        this.internalKafkaProducer.publish(gson.toJson(payload));
    }

    @Override
    public void publishListOnKafkaOfficialTopic(List<StationCongestion> payload){
        log.debug("publish list of {} elements into 'PeopleCountingData' bulk topic", payload.size());

        this.officialKafkaProducer.publish(gson.toJson(payload));
    }
}

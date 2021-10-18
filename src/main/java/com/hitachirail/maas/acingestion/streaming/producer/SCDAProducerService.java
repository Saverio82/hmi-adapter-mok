package com.hitachirail.maas.acingestion.streaming.producer;

import com.google.gson.Gson;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.SeatCountingDataAggregate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@MaasProducerUser
@Slf4j
public class SCDAProducerService implements ProducerService<SeatCountingDataAggregate> {

    @MaasProducer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private Gson gson;

    @Autowired
    public SCDAProducerService(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<SeatCountingDataAggregate> payload) {
        log.debug("publish list of {} elements into 'SeatCountingDataAggregate' bulk topic", payload.size());

        this.internalKafkaProducer.publish(gson.toJson(payload));
    }

    @Override
    public void publishListOnKafkaOfficialTopic(List<SeatCountingDataAggregate> payload) {
        log.debug("publish list of {} elements into 'SeatCountingDataAggregate' bulk topic", payload.size());

        this.officialKafkaProducer.publish(gson.toJson(payload));
    }
}

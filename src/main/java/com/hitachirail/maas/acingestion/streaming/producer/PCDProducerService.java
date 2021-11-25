package com.hitachirail.maas.acingestion.streaming.producer;

import com.google.gson.Gson;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasProducerUser;
import com.hitachi.maas.ilspringlibrary.streaming.producer.MaasProducerComponent;
import com.hitachirail.maas.acingestion.beans.PeopleCountingData;
import com.hitachirail.maas.acingestion.businessentity.BusinessPeopleCountingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


@MaasProducerUser
@Slf4j
public class PCDProducerService implements ProducerService<BusinessPeopleCountingData>{

    @MaasProducer(
            kafkaTopic = "${kafka.people.counting.data.bulk.topic}"
    )
    private MaasProducerComponent internalKafkaProducer;

    @MaasProducer(
            kafkaTopic = "${kafka.people.counting.data.topic}"
    )
    private MaasProducerComponent officialKafkaProducer;

    private Gson gson;

    @Autowired
    public PCDProducerService(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void publishListOnKafkaBulkTopic(List<BusinessPeopleCountingData> payload) {
         log.debug("publish list of {} elements into 'PeopleCountingData' bulk topic", payload.size());

         this.internalKafkaProducer.publish(gson.toJson(payload));
    }

    @Override
    public void publishListOnKafkaOfficialTopic(List<BusinessPeopleCountingData> payload){
        log.debug("publish list of {} elements into 'PeopleCountingData' bulk topic", payload.size());

        this.officialKafkaProducer.publish(gson.toJson(payload));
    }


}

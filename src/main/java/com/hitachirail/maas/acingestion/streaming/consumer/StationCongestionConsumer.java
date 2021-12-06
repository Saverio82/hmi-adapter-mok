package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.StationCongestion;
import com.hitachirail.maas.acingestion.businessentity.factory.StationCongestionBuilder;
import com.hitachirail.maas.acingestion.dto.StationCongestionDTO;
import com.hitachirail.maas.acingestion.streaming.consumer.utils.BusinessObjectUtils;
import com.hitachirail.maas.acingestion.streaming.producer.ProducerService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@MaasConsumerFactory
@Slf4j
public class StationCongestionConsumer {

    private ObjectMapper objectMapper;
    private ProducerService<StationCongestion> stationCongestionProducer;
    BusinessObjectUtils businessObjectUtils;

    @Autowired
    public StationCongestionConsumer(ObjectMapper objectMapper, ProducerService<StationCongestion> stationCongestionProducer, BusinessObjectUtils businessObjectUtils){
        this.objectMapper = objectMapper;
        this.stationCongestionProducer = stationCongestionProducer;
        this.businessObjectUtils = businessObjectUtils;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","StationCongestionBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.station.congestion.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"
    )
    public void consumeStationCongestionTopic(List<String> messages) throws JsonProcessingException {
        log.info("consume messages on 'StationCongestionBulkTopic' topic.");

        List<StationCongestionDTO> stationCongestionList = new ArrayList<>();

        for(String message : messages)
            stationCongestionList.addAll(objectMapper.readValue(message, new TypeReference<List<StationCongestionDTO>>(){}));

        log.debug("StationCongestion list size extracted: {}", stationCongestionList.size());

        //TODO: Entity enrichment

       // this.stationCongestionProducer.publishListOnKafkaOfficialTopic(stationCongestionList);
        for(StationCongestionDTO stationCongestion : stationCongestionList){

            StationCongestion sc = StationCongestionBuilder.buildStationCongestionBusiness
                    (stationCongestion,businessObjectUtils.getTenantId(stationCongestion.getOperator()));

            this.stationCongestionProducer.publishOnKafkaOfficialTopic(sc);
        }

    }

}

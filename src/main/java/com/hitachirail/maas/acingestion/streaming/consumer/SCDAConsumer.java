package com.hitachirail.maas.acingestion.streaming.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumer;
import com.hitachi.maas.ilspringlibrary.streaming.annotation.MaasConsumerFactory;
import com.hitachirail.maas.acingestion.businessentity.SeatCountingDataAggregate;
import com.hitachirail.maas.acingestion.businessentity.factory.SCDABusinessFactory;
import com.hitachirail.maas.acingestion.dto.SeatCountingDataAggregateDTO;
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
public class SCDAConsumer {

    private ProducerService<BusinessObjectWrapper<SeatCountingDataAggregate>> seatCountingDataAggregateProducer;

    private ObjectMapper objectMapper;

    BusinessObjectUtils businessObjectUtils;

    @Autowired
    public SCDAConsumer(ObjectMapper objectMapper, ProducerService<BusinessObjectWrapper<com.hitachirail.maas.acingestion.businessentity.SeatCountingDataAggregate>> seatCountingDataAggregateProducer, BusinessObjectUtils businessObjectUtils) {
        this.objectMapper = objectMapper;
        this.seatCountingDataAggregateProducer = seatCountingDataAggregateProducer;
        this.businessObjectUtils=businessObjectUtils;
    }

    @Timed(value="maas.kafka.consumer", extraTags = {"type","SeatCountingDataAggregateBulk"})
    @MaasConsumer(
            kafkaTopic = "${kafka.seat.counting.data.aggregate.bulk.topic}",
            consumerGroupId = "${consumer.data.ingestion.group.id}"

    )
    public void consumeSCDATopic(List<String> messages) throws JsonProcessingException {
        log.info("consume messages on 'SeatCountingDataAggregate' topic.");

        List<SeatCountingDataAggregateDTO> seatCountingDataAggregateList = new ArrayList<>();

        for(String message : messages)
            seatCountingDataAggregateList.addAll(objectMapper.readValue(message, new TypeReference<List<SeatCountingDataAggregateDTO>>(){}));

        log.debug("SeatCountingDataAggregate list size extracted: {}", seatCountingDataAggregateList.size());

        for(SeatCountingDataAggregateDTO seatCountingDataAggregate:  seatCountingDataAggregateList){
            BusinessObjectWrapper<SeatCountingDataAggregate>wrapper=SCDABusinessFactory.createSCDABusiness(seatCountingDataAggregate,
                    businessObjectUtils.getTenantId(seatCountingDataAggregate.getOperator()));
            this.seatCountingDataAggregateProducer.publishOnKafkaOfficialTopic(wrapper);
        }

    }

}

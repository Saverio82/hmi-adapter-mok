package com.hitachirail.maas.acingestion.streaming.producer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProducerService<T> {
    default void publishOnKafkaBulkTopic(T payload) {}
    default void publishListOnKafkaBulkTopic(List<T> payload) throws JsonProcessingException {}

    default void publishOnKafkaOfficialTopic(T payload) throws JsonProcessingException {}
    default void publishListOnKafkaOfficialTopic(List<T> payload) throws JsonProcessingException {}
}

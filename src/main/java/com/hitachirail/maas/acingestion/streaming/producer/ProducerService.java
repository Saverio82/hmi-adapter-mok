package com.hitachirail.maas.acingestion.streaming.producer;

import java.util.List;

public interface ProducerService<T> {
    default void publishOnKafkaBulkTopic(T payload) {}
    default void publishListOnKafkaBulkTopic(List<T> payload) {}

    default void publishOnKafkaOfficialTopic(T payload) {}
    default void publishListOnKafkaOfficialTopic(List<T> payload) {}
}

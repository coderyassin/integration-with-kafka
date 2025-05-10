package org.yascode.integration_with_kafka.service.kafka.api;

public interface KafkaProducerService {

    void sendMessage(String message);
}

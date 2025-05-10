package org.yascode.integration_with_kafka.server.rest.api;

public interface KafkaApi {

    String sendMessage(String message);
}

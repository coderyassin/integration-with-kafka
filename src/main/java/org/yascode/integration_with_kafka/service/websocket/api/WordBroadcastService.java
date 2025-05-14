package org.yascode.integration_with_kafka.service.websocket.api;

@FunctionalInterface
public interface WordBroadcastService {

    void sendRandomWord();
}

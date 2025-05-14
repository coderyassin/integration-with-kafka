package org.yascode.integration_with_kafka.service.websocket.api;

import org.yascode.integration_with_kafka.model.Message;

public interface WebSocketService {

    void connect(String url);

    void disconnect();

    void sendMessage(String destination, Message message);
}

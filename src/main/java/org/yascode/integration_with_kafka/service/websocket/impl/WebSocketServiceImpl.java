package org.yascode.integration_with_kafka.service.websocket.impl;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.yascode.integration_with_kafka.model.Message;
import org.yascode.integration_with_kafka.service.websocket.api.WebSocketService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    //private final WebSocketStompClient webSocketStompClient;

    private final StompSessionHandler customStompSessionHandler;

    private StompSession stompSession;

    public WebSocketServiceImpl(/*WebSocketStompClient webSocketStompClient,*/
                                StompSessionHandler customStompSessionHandler) {
        //this.webSocketStompClient = webSocketStompClient;
        this.customStompSessionHandler = customStompSessionHandler;
    }

    @Override
    public void connect(String url) {
        /*try {
            stompSession = webSocketStompClient.connect(url, customStompSessionHandler)
                    .get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Error connecting to WebSocket", e);
        }*/
    }

    @Override
    public void disconnect() {
        if (stompSession != null && stompSession.isConnected()) {
            stompSession.disconnect();
        }
    }

    @Override
    public void sendMessage(String destination, Message message) {
        if (stompSession != null && stompSession.isConnected()) {
            stompSession.send(destination, message);
        } else {
            throw new IllegalStateException("Not connected to WebSocket server");
        }
    }
}

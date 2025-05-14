package org.yascode.integration_with_kafka.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.yascode.integration_with_kafka.model.Message;

import java.lang.reflect.Type;

@Component
@Slf4j
public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session established : {}", session.getSessionId());

        session.subscribe("/topic/messages", this);

        session.send("/app/chat", new Message("Hello from Spring Boot!"));
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        log.info("Received : {}", msg.getContent());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }
}

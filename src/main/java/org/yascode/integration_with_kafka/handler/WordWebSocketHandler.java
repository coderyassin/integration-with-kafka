package org.yascode.integration_with_kafka.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WordWebSocketHandler extends TextWebSocketHandler {

    private final Map<WebSocketSession, ScheduledExecutorService> sessions = new ConcurrentHashMap<>();

    private final Random random = new Random();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        sessions.put(session, scheduler);

        scheduler.scheduleAtFixedRate(() -> {
                    try {
                        String word = getRandomWord(random.nextInt(12));
                        session.sendMessage(new TextMessage(word));
                    } catch (Exception e) {
                        log.error("Got an exception", e);
                    }
                },
                0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        ScheduledExecutorService scheduler = sessions.remove(session);
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    private String getRandomWord(int length) {
        String candidateChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder ();
        Random random = new Random ();
        for (int i = 0; i < length; i ++) {
            sb.append (candidateChars.charAt (random.nextInt (candidateChars
                    .length ())));
        }

        return sb.toString ();
    }

}

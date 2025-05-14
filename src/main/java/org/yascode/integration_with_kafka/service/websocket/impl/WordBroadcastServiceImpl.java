package org.yascode.integration_with_kafka.service.websocket.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.yascode.integration_with_kafka.service.websocket.api.WordBroadcastService;

import java.util.Random;

@Service
public class WordBroadcastServiceImpl implements WordBroadcastService {

    private final SimpMessagingTemplate messagingTemplate;

    public WordBroadcastServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    @Scheduled(fixedRate = 2000)
    public void sendRandomWord() {
        String candidateChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder ();
        Random random = new Random ();
        int length = random.nextInt(12);

        length = length > 0 ? length : 10;

        for (int i = 0; i < length; i ++) {
            sb.append (candidateChars.charAt (random.nextInt (candidateChars
                    .length ())));
        }

        String word = sb.toString ();

        messagingTemplate.convertAndSend("/topic/words", word);
    }
}

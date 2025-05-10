package org.yascode.integration_with_kafka.service.kafka.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.yascode.integration_with_kafka.service.kafka.api.KafkaProducerService;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String TOPIC = "yascode_topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        log.info("Message sent: {}", message);
    }
}

package org.yascode.integration_with_kafka.service.kafka.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.yascode.integration_with_kafka.service.kafka.api.KafkaConsumerService;

@Service
@Slf4j
public class MainConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = "yascode_topic", groupId = "group-id-79")
    public void consume(String message) {
        log.info("Message received from the primary consumer: {}", message);
    }
}

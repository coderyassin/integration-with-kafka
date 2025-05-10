package org.yascode.integration_with_kafka.server.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.yascode.integration_with_kafka.server.rest.api.KafkaApi;
import org.yascode.integration_with_kafka.service.kafka.api.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController implements KafkaApi {
    private final KafkaProducerService kafkaProducerService;

    @Override
    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return "Message sent successfully";
    }
}

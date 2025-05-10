package org.yascode.integration_with_kafka.server.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.integration_with_kafka.server.rest.api.StreamApi;
import org.yascode.integration_with_kafka.service.kafka.api.KafkaProducerService;
import org.yascode.integration_with_kafka.service.stream.api.GetNumberStreamService;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/stream")
public class StreamController implements StreamApi {
    private final GetNumberStreamService getNumberStreamService;
    private final KafkaProducerService kafkaProducerService;

    public StreamController(GetNumberStreamService getNumberStreamService,
                            KafkaProducerService kafkaProducerService) {
        this.getNumberStreamService = getNumberStreamService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    @GetMapping(value = "/numbers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> streamNumbers() {
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(i -> i.intValue() + 1);
    }

    @Override
    @GetMapping(value = "/numbers-in-string", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> triggerStream() {
        return getNumberStreamService.streamNumbers()
                .map(number -> {
                    int i = number * 2;
                    kafkaProducerService.sendMessage(String.valueOf(i));
                    return i;
                });
    }

}

package org.yascode.integration_with_kafka.service.stream.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.yascode.integration_with_kafka.service.stream.api.GetNumberStreamService;
import reactor.core.publisher.Flux;

@Service
public class GetNumberStreamServiceImpl implements GetNumberStreamService {
    private final WebClient webClient;

    public GetNumberStreamServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public Flux<Integer> streamNumbers() {
        return webClient.get()
                .uri("/stream/numbers")
                .retrieve()
                .bodyToFlux(Integer.class)
                .map(number -> number * 2);
    }
}

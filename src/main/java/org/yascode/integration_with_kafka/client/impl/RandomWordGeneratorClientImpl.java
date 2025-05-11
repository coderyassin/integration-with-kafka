package org.yascode.integration_with_kafka.client.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yascode.integration_with_kafka.client.api.RandomWordGeneratorClient;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class RandomWordGeneratorClientImpl implements RandomWordGeneratorClient {

    private static final String URL = "https://random-word-api.vercel.app/api?words={words}";

    private final RestTemplate restTemplate;

    public RandomWordGeneratorClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CompletableFuture<List<String>> getThreeRandomWord() {
        return getRandomWord(3);
    }

    @Override
    public CompletableFuture<List<String>> getFiveRandomWord() {
        return getRandomWord(5);
    }

    @Override
    public CompletableFuture<List<String>> getEightRandomWord() {
        return getRandomWord(8);
    }

    private CompletableFuture<List<String>> getRandomWord(int words) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("words", words);

            ParameterizedTypeReference<List<String>> typeRef =
                    new ParameterizedTypeReference<>() {};

            ResponseEntity<List<String>> response = restTemplate.exchange(URL, HttpMethod.GET, null, typeRef, map);

            if (Objects.nonNull(response) && response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            return Collections.emptyList();
        });
    }
}

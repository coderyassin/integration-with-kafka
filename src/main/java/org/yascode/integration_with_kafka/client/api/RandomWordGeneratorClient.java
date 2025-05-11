package org.yascode.integration_with_kafka.client.api;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RandomWordGeneratorClient {

    CompletableFuture<List<String>> getThreeRandomWord();

    CompletableFuture<List<String>> getFiveRandomWord();

    CompletableFuture<List<String>> getEightRandomWord();
}

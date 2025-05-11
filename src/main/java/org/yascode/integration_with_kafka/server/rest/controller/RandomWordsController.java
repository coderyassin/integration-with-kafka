package org.yascode.integration_with_kafka.server.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.integration_with_kafka.server.rest.api.RandomWordsApi;
import org.yascode.integration_with_kafka.service.word.api.RandomWordsService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/random-words")
public class RandomWordsController implements RandomWordsApi {

    private final RandomWordsService randomWordsService;

    public RandomWordsController(RandomWordsService randomWordsService) {
        this.randomWordsService = randomWordsService;
    }

    @Override
    @GetMapping(value = "/by-vercel")
    public CompletableFuture<List<String>> getRandomWords() {
        return randomWordsService.getRandomWords();
    }
}

package org.yascode.integration_with_kafka.service.word.impl;

import org.springframework.stereotype.Service;
import org.yascode.integration_with_kafka.client.api.RandomWordGeneratorClient;
import org.yascode.integration_with_kafka.service.word.api.RandomWordsService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RandomWordsServiceImpl implements RandomWordsService {

    private final RandomWordGeneratorClient randomWordGeneratorClient;

    public RandomWordsServiceImpl(RandomWordGeneratorClient randomWordGeneratorClient) {
        this.randomWordGeneratorClient = randomWordGeneratorClient;
    }

    @Override
    public CompletableFuture<List<String>> getRandomWords() {

        CompletableFuture<List<String>> threeRandomWords = randomWordGeneratorClient.getThreeRandomWord();

        CompletableFuture<List<String>> fiveRandomWords = randomWordGeneratorClient.getFiveRandomWord();

        CompletableFuture<List<String>> eightRandomWords = randomWordGeneratorClient.getEightRandomWord();

        return threeRandomWords.thenCombine(fiveRandomWords, (threeWords, fiveWords) -> {
            List<String> combined = new ArrayList<>();
            combined.addAll(threeWords);
            combined.addAll(fiveWords);
            return combined;
        }).thenCombine(eightRandomWords, (combined_3_5, eightWords) -> {
            combined_3_5.addAll(eightWords);
            return combined_3_5.stream()
                    .distinct()
                    .toList();
        });
    }
}

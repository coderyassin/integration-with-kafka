package org.yascode.integration_with_kafka.service.word.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RandomWordsService {

    CompletableFuture<List<String>> getRandomWords();
}

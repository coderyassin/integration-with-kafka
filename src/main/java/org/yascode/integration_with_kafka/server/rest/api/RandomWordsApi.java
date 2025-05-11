package org.yascode.integration_with_kafka.server.rest.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RandomWordsApi {

    CompletableFuture<List<String>> getRandomWords();
}

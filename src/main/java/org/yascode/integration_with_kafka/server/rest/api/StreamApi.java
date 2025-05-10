package org.yascode.integration_with_kafka.server.rest.api;

import reactor.core.publisher.Flux;

public interface StreamApi {

    Flux<Integer> streamNumbers();

    Flux<Integer> triggerStream();
}

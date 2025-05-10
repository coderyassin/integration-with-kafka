package org.yascode.integration_with_kafka.service.stream.api;

import reactor.core.publisher.Flux;

public interface GetNumberStreamService {

    Flux<Integer> streamNumbers();
}

package org.yascode.integration_with_kafka.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(
                "yascode_topic",
                Consumed.with(Serdes.String(), Serdes.String())
        );

        stream
                .mapValues(value -> "Processed: " + value)
                .to("yascode_output_topic", Produced.with(Serdes.String(), Serdes.String()));

        return stream;
    }
}

package org.yascode.integration_with_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IntegrationWithKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationWithKafkaApplication.class, args);
	}

}

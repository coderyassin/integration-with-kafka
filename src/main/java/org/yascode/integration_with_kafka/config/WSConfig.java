package org.yascode.integration_with_kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.yascode.integration_with_kafka.handler.WordWebSocketHandler;

@Configuration
@EnableWebSocket
public class WSConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WordWebSocketHandler(), "/ws/words")
                .setAllowedOrigins("*");
    }
}

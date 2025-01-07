package org.aliezkan.chatapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private static final String WEB_SOCKET_ENDPOINT = "/ws";
    private static final String APPLICATION_DESTINATION_PREFIX = "/app";
    private static final String BROKER_DESTINATION_PREFIX = "/topic";


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WEB_SOCKET_ENDPOINT).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(APPLICATION_DESTINATION_PREFIX);
        registry.enableSimpleBroker(BROKER_DESTINATION_PREFIX);
    }
}

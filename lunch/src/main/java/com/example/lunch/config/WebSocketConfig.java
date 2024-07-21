package com.example.lunch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  @Value("${relay.mq.host}")
  String host;
  @Value("${relay.mq.port}")
  int port;
  @Value("${relay.mq.pw}")
  String pw;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableStompBrokerRelay("/queue", "/topic")
            .setRelayHost(host)
            .setRelayPort(port)
            .setClientLogin("guest")
            .setClientPasscode(pw);

    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
  }

}
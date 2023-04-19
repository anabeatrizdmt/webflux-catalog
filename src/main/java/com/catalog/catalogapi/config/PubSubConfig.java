package com.catalog.catalogapi.config;

import com.catalog.catalogapi.pubsub.PubSubMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class PubSubConfig {

    @Bean
    public Sinks.Many<PubSubMessage> sink() {
        return Sinks.many()
                .multicast()
                .onBackpressureBuffer(1024);
    }

}
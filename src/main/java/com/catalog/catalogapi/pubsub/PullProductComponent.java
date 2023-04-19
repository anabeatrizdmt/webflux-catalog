package com.catalog.catalogapi.pubsub;

import com.catalog.catalogapi.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@RequiredArgsConstructor
public class PullProductComponent {

    private final Sinks.Many<PubSubMessage> sink;
    public Mono<Product> pullNewProduct(final Product product) {
        return Mono.fromCallable(() -> {
                    log.info("Starting product pull - {}", product);
                    String id = product.getId();
                    return new PubSubMessage(id, product);
                })
                .subscribeOn(Schedulers.parallel())
                .doOnNext(this.sink::tryEmitNext)
                .doOnNext(event -> log.info("Product event created - {}", event))
                .thenReturn(product);
    }
}

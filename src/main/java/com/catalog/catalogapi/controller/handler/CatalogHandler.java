package com.catalog.catalogapi.controller.handler;

import com.catalog.catalogapi.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class CatalogHandler {

    private static final List<Product> PRODUCT_LIST =
            List.of(
                new Product(UUID.randomUUID().toString(), "Coca-Cola", new BigDecimal("2.5"), 100L),
                new Product(UUID.randomUUID().toString(), "Coca-Cola Light", new BigDecimal("2.5"), 100L),
                new Product(UUID.randomUUID().toString(), "Pepsi", new BigDecimal("2.5"), 100L),
                new Product(UUID.randomUUID().toString(), "Fanta", new BigDecimal("2.5"), 100L),
                new Product(UUID.randomUUID().toString(), "Sprite", new BigDecimal("2.5"), 100L)
            );

    public Mono<ServerResponse> getAll(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getProducts(), new ParameterizedTypeReference<List<Product>>() {}));

    }

    public Mono<ServerResponse> getById(ServerRequest request) {

        String id = request.pathVariable("id");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(getProductsById(id), Product.class));

    }

    public Mono<List<Product>> getProducts() {
        return Mono.just(PRODUCT_LIST);
    }

    public Mono<Product> getProductsById(String id) {
        return getProducts()
                .flatMapMany(Flux::fromIterable)
                .filter(product -> product.getId().equals(id))
                .singleOrEmpty();
    }
}

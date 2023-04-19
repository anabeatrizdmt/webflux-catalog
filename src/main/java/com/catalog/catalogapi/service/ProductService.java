package com.catalog.catalogapi.service;

import com.catalog.catalogapi.controller.ProductController;
import com.catalog.catalogapi.model.Product;
import com.catalog.catalogapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Flux<Product> get() {
        log.info("Searching all products");

        return repository.findAll()
                .subscribeOn(Schedulers.parallel())
                .doOnNext(product -> log.info("Getting user from database - {}"));
    }

    public Mono<Product> save(ProductController.NewProductRequest request) {
        log.info("Saving product - {}", request);

        final var newId = UUID.randomUUID().toString();

        final var newUser = Product.builder()
                .id(newId)
                .name(request.getName())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .build();


        return Mono.defer(() -> {
            log.info("persisting new user in the database- {}", newId);
            return repository.save(newUser);
        }).subscribeOn(Schedulers.boundedElastic());
    }

}

package com.catalog.catalogapi.controller;

import com.catalog.catalogapi.model.Product;
import com.catalog.catalogapi.pubsub.PullProductComponent;
import com.catalog.catalogapi.service.ProductService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final PullProductComponent pullProductComponent;

    @PostMapping
    public Mono<Product> newProduct(@RequestBody NewProductRequest request) {

        return Mono.defer(() -> service.save(request))
                .flatMap(product -> pullProductComponent.pullNewProduct(product))
                .subscribeOn(Schedulers.parallel());
    }
    @GetMapping("/all")
    public Flux<Product> get() {
        return service.get();
    }

    @Data
    public static class NewProductRequest {
        @JsonProperty("name")
        private String name;
        @JsonProperty("price")
        private BigDecimal price;
        @JsonProperty("availableQuantity")
        private Long availableQuantity;
    }
}

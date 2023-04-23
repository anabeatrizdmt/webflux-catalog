package com.catalog.catalogapi.controller.handler;

import com.catalog.catalogapi.dto.CatalogRequest;
import com.catalog.catalogapi.dto.CatalogResponse;
import com.catalog.catalogapi.dto.StockRequest;
import com.catalog.catalogapi.model.Product;
import com.catalog.catalogapi.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogHandler {

    private final CatalogService catalogService;

    public Mono<ServerResponse> save(ServerRequest request) {

        return request.bodyToMono(CatalogRequest.class)
                .flatMap(catalogService::save)
                .flatMap(response -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response)));
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<CatalogResponse> catalogResponses = catalogService
                .getAll()
                .map(product -> CatalogResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .availableQuantity(product.getAvailableQuantity())
                        .build());

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(catalogResponses, CatalogResponse.class));
    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");
        Mono<CatalogResponse> responseMono = catalogService.findById(id)
                .map(product -> new CatalogResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getAvailableQuantity()
                ));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(responseMono, CatalogResponse.class));

    }

    public Mono<ServerResponse> getStock(ServerRequest request) {
        Mono<List<String>> ids = request.bodyToMono(new ParameterizedTypeReference<List<String>>() {});

        Flux<Product> products = ids
                .flatMapMany(catalogService::getStock);

        return ServerResponse.ok().body(products, Product.class);
    }
}

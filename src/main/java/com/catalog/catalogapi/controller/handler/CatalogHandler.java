package com.catalog.catalogapi.controller.handler;

import com.catalog.catalogapi.dto.CatalogRequest;
import com.catalog.catalogapi.dto.CatalogResponse;
import com.catalog.catalogapi.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(catalogService.findById(id), CatalogResponse.class));

    }

}

package com.catalog.catalogapi.service;

import com.catalog.catalogapi.dto.CatalogRequest;
import com.catalog.catalogapi.dto.CatalogResponse;
import com.catalog.catalogapi.model.Product;
import com.catalog.catalogapi.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository repository;

    public Mono<CatalogResponse> save(CatalogRequest catalogRequest) {

        String uid = UUID.randomUUID().toString();

        var catalogEntity = new Product(
                uid,
                catalogRequest.name(),
                catalogRequest.price(),
                catalogRequest.availableQuantity()
        );

        repository.save(catalogEntity);

        return Mono.defer(() -> Mono.just(
                new CatalogResponse(
                        uid,
                        catalogRequest.name(),
                        catalogRequest.price(),
                        catalogRequest.availableQuantity()
                )
        ));
    }

    public Mono<CatalogResponse> findById(String id) {
        return Mono.defer(() -> Mono.justOrEmpty(repository.findById(id)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(entity -> {
                    return new CatalogResponse(
                            entity.id(),
                            entity.name(),
                            entity.price(),
                            entity.availableQuantity()
                    );
                });
    }
}

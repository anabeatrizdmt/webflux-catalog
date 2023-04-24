package com.catalog.catalogapi.service;

import com.catalog.catalogapi.dto.CatalogRequest;
import com.catalog.catalogapi.dto.ProductUpdateRequest;
import com.catalog.catalogapi.model.Product;
import com.catalog.catalogapi.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository repository;

    public Mono<Product> save(CatalogRequest request) {
        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .build();
        return repository.save(product);
    }

    public Flux<Product> getAll() {
        return repository.findAll();
    }

    public Mono<Product> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Product> getStock(List<String> ids) {
        return repository.findAllById(ids);
    }

    public Mono<Product> updateProductStock(String productId, Long purchasedQuantity) {

        return repository.findById(productId)
                .flatMap(product -> {
                    Long currentStock = product.getAvailableQuantity();
                    if (currentStock < purchasedQuantity) {
                        throw new IllegalArgumentException("Not enough stock for product: " + productId);
                    }
                    Long newStock = currentStock - purchasedQuantity;
                    product.setAvailableQuantity(newStock);
                    return repository.save(product);
                });
    }
}

package com.catalog.catalogapi.repository;

import com.catalog.catalogapi.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {
}

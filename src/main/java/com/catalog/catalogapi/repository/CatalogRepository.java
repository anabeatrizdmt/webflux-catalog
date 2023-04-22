package com.catalog.catalogapi.repository;

import com.catalog.catalogapi.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatalogRepository extends ReactiveMongoRepository<Product, String> {
}

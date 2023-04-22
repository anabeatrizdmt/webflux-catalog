package com.catalog.catalogapi.repository;

import com.catalog.catalogapi.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CatalogRepository {

    private static final List<Product> catalog_db = new CopyOnWriteArrayList<>();

    public void save(Product product) {
        catalog_db.add(product);
    }

    public Optional<Product> findById(String id) {
        return catalog_db.stream()
                .filter(entity -> entity.id().equals(id))
                .findFirst();
    }

}

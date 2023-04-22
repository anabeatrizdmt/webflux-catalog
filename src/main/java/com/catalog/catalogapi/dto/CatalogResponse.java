package com.catalog.catalogapi.dto;

import java.math.BigDecimal;

public record CatalogResponse (
        String id,
        String name,
        BigDecimal price,
        Long availableQuantity
) {
}

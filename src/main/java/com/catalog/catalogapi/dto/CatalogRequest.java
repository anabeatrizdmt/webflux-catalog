package com.catalog.catalogapi.dto;

import java.math.BigDecimal;

public record CatalogRequest (
        String name,
        BigDecimal price,
        Long availableQuantity
) {
}

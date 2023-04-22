package com.catalog.catalogapi.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CatalogResponse (
        String id,
        String name,
        BigDecimal price,
        Long availableQuantity
) {
}

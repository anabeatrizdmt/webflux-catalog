package com.catalog.catalogapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@With
@Document(value = "product")
public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private Long availableQuantity;
}

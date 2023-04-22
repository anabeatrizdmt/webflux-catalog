package com.catalog.catalogapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@Builder
@With
@Document(value = "catalog")
public class Product {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private Long availableQuantity;
}

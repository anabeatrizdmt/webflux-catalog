package com.catalog.catalogapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

//@Data
//@Builder
//@With
//@Document(value = "catalog")
public record Product (String id,
                      String name,
                      BigDecimal price,
                      Long availableQuantity
) {
}

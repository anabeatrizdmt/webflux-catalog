package com.catalog.catalogapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockRequest {

    @JsonProperty("productId")
    private String id;
}

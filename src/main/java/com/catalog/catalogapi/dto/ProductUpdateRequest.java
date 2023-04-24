package com.catalog.catalogapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ProductUpdateRequest {
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("purchasedQuantity")
    private int purchasedQuantity;

}

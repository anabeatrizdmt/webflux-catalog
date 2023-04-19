package com.catalog.catalogapi.pubsub;

import com.catalog.catalogapi.model.Product;

public record PubSubMessage(String id, Product product) {
}

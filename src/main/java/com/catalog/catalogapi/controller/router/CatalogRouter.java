package com.catalog.catalogapi.controller.router;

import com.catalog.catalogapi.controller.handler.CatalogHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@RequiredArgsConstructor
public class CatalogRouter {

    private final CatalogHandler catalogHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(POST("/catalog"), catalogHandler::save)
                .andRoute(GET("/catalog"), catalogHandler::getAll)
                .andRoute(GET("/catalog/{id}"), catalogHandler::findById);
    }
}

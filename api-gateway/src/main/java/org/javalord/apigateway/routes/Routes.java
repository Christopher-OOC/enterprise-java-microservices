package org.javalord.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> getRoutes() {
        return route().GET("/api/products/**", http())
                .before(uri("http://localhost:8001"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> getRoute() {
        return route("errtt").GET("/get", http())
                .before(uri("https://example.org"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> anyMethodRoute() {
        return GatewayRouterFunctions.route("sddeee")
                .route(RequestPredicates.path("/get"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("https://example.org"))
                .build();
    }
}

package org.javalord.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Routes {

    // a particular request method
    @Bean
    public RouterFunction<ServerResponse> getRoutes() {
        return route().GET("/api/products/**", http())
                .before(uri("http://localhost:8001"))
                .build();
    }

    // a particular request method
    @Bean
    public RouterFunction<ServerResponse> getRoute() {
        return route("product-service").GET("/get", http())
                .before(uri("https://example.org"))
                .build();
    }

    // for any request method
    @Bean
    public RouterFunction<ServerResponse> anyMethodRoute() {
        return GatewayRouterFunctions.route("product-service")
                .route(RequestPredicates.path("/api/products/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8001"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product-service-breaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> anyProductDocs() {
        return GatewayRouterFunctions.route("product-service-swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8001"))
                .before(BeforeFilterFunctions.setPath("/v3/api-docs"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product-service-swagger-breaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> anyOrderDocs() {
        return GatewayRouterFunctions.route("order-service-swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8002"))
                .before(BeforeFilterFunctions.setPath("/v3/api-docs"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("order-service-swagger-breaker", URI.create("forward:/fallbackRoute")))

                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> anyInventoryDocs() {
        return GatewayRouterFunctions.route("inventory-service-swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8003"))
                .before(BeforeFilterFunctions.setPath("/v3/api-docs"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventory-service-swagger-breaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .route(RequestPredicates.GET("/fallbackRoute"), request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service unavailable, try again")).build();
    }

}

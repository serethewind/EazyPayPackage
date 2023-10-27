package com.eazypay.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }


    @Bean
    public RouteLocator eazyPayRouteConfiguration(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(predicateDefinition -> predicateDefinition
                        .path("/eazypay/accounts/**")
                        .filters(filterDefinition -> filterDefinition
                                .rewritePath("/eazypay/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        )
                        .uri("lb://ACCOUNTS")
                )
                .route(predicateDefinition -> predicateDefinition
                        .path("/eazypay/cards/**")
                        .filters(filterDefinition -> filterDefinition
                                .rewritePath("/eazypay/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        )
                        .uri("lb://CARDS")
                )
                .route(predicateDefinition -> predicateDefinition
                        .path("/eazypay/loans/**")
                        .filters(filterDefinition -> filterDefinition
                                .rewritePath("/eazypay/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        )
                        .uri("lb://LOANS")
                )
                .build();
    }

}

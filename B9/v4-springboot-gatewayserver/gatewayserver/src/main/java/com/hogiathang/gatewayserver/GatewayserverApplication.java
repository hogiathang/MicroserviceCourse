package com.hogiathang.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.cache.LocalResponseCacheGatewayFilterFactory;
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
	public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
							.path("/hogiathang/accounts/**")
							.filters(f -> f.rewritePath("/hogiathang/accounts/?(?<remaining>.*)", "/${remaining}")
									.addRequestHeader("X-Response-Header", LocalDateTime.now().toString()))
							.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/hogiathang/loans/**")
						.filters(f -> f.rewritePath("/hogiathang/loans/?(?<remaining>.*)", "/${remaining}")
								.addRequestHeader("X-Response-Header", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/hogiathang/cards/**")
						.filters(f -> f.rewritePath("/hogiathang/cards/?(?<remaining>.*)", "/${remaining}")
								.addRequestHeader("X-Response-Header", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.build();
	}
}

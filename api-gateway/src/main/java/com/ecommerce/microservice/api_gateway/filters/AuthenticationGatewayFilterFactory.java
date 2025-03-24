package com.ecommerce.microservice.api_gateway.filters;

import com.ecommerce.microservice.api_gateway.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {
    private final JwtService jwtService;
    public AuthenticationGatewayFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if(!config.isEnabled){
                return chain.filter(exchange);
            }

            String auhtorizationheader=exchange.getRequest().getHeaders().getFirst("Authorization");
            if(auhtorizationheader==null){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token=auhtorizationheader.split("Bearer ")[1];
            Long userId= jwtService.getUserIdFromToken(token);

            ServerHttpRequest mutatedRequest =exchange.getRequest().mutate().header("X-User-Id",userId.toString()).build();
            return chain.filter(exchange.mutate().request(mutatedRequest).build());
           //` return chain.filter(exchange);
        };
    }

    public static class Config{
        private  boolean isEnabled;
    }
}

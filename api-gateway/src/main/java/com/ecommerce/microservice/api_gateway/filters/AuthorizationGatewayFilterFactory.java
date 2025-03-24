package com.ecommerce.microservice.api_gateway.filters;

import com.ecommerce.microservice.api_gateway.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Slf4j
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {
    private final JwtService jwtService;
    public AuthorizationGatewayFilterFactory(JwtService jwtService) {
        super(AuthorizationGatewayFilterFactory.Config.class);
        this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(AuthorizationGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {

//            if(!config.isEnabled){
//                return chain.filter(exchange);
//            }

            String authorizationheader=exchange.getRequest().getHeaders().getFirst("Authorization");
            if(authorizationheader==null){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token=authorizationheader.split("Bearer ")[1];
            List<String> userRole= jwtService.getUserRoleFromToken(token);
            if(!userRole.contains("ADMIN")){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();

            }
            ServerHttpRequest mutatedRequest =exchange.getRequest().mutate().header("X-User-Id","12").build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
           //  return chain.filter(exchange);
        };
    }

    public static class Config{
        private  boolean isEnabled;
    }
}

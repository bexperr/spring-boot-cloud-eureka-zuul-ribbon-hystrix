package com.bexperr.app.ms.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component 
public class SampleGlobalFilter implements GlobalFilter, Ordered{

    private final Logger LOGGER = LoggerFactory.getLogger(SampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Ejecutando filter antes del PRE request");

        exchange.getRequest().mutate().headers(h->h.add("token", "abcd123"));


        return chain.filter(exchange).then(
            Mono.fromRunnable(()->{
                LOGGER.info("ejecutando filtro POST response");
                String tokenStr = exchange.getRequest().getHeaders().get("token").get(0);
                LOGGER.info("token {}",tokenStr);

                //get token de forma funcional
                Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(value ->{
                    LOGGER.info("token funcional {}",value);
                    exchange.getResponse().getHeaders().add("token", value);
                });;

                exchange.getResponse().getCookies().add("color", ResponseCookie.from("color","red").build());
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            })
        );
        
    }

    @Override
    public int getOrder() {
       return 100;
    }



}

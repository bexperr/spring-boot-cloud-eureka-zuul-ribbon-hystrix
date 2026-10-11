package com.bexperr.app.ms.gateway.filters;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component 
public class SampleCookieGatewayFilterFactory extends AbstractGatewayFilterFactory<SampleCookieGatewayFilterFactory.ConfigurationCookie>{

    private final Logger LOGGER = LoggerFactory.getLogger(SampleCookieGatewayFilterFactory.class);

    public SampleCookieGatewayFilterFactory() {
        super(ConfigurationCookie.class);
    }


    @Override
    public GatewayFilter apply(ConfigurationCookie config) {
        return new OrderedGatewayFilter((exchange,chain) -> {
            LOGGER.info("ejecutando PRE gateway filter factory {}",config.message);
            
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                Optional.ofNullable(config.value).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.name,cookie).build());
                });

            LOGGER.info("ejecutando POST gateway filter factory {}",config.message);
            }));
        }, 100);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // Configuracion para el application.yaml
        // - SampleCookie=Hola mi mensaje personalizado para productos, user, Ulises
        return Arrays.asList("message","name","value");
    }





    public static class ConfigurationCookie{
        private String name;
        private String value;
        private String message;

        public ConfigurationCookie(){}

        public ConfigurationCookie(String name, String value, String message) {
            this.name = name;
            this.value = value;
            this.message = message;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        
    }

    

}

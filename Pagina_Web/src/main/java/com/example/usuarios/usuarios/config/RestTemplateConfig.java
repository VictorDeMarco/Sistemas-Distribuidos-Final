package com.example.usuarios.usuarios.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
/**
 * Clase RestTemplateConfig.
 */
public class RestTemplateConfig {

    @Bean
/**
 * Método restTemplate.
 * @return valor de retorno
 */
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

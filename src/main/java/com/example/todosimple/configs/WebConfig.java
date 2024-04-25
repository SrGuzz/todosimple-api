package com.example.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer { //Configuração paraliberar o front puxar os dados
    
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**");
    }
}

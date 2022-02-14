package com.capgemini;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagesConfigurer implements WebMvcConfigurer{
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/resources/**").addResourceLocations("file:" + "/home/curso/projectfinal/resources"); 
    }
}

package com.capgemini;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagesConfigurer implements WebMvcConfigurer{
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);

<<<<<<< HEAD
        registry.addResourceHandler("/resources/**").addResourceLocations("file:" + "/home/curso/projectfinal/resources"); 
=======
        registry.addResourceHandler("/resources/**").addResourceLocations("file:" + "C://Resources"); //TODO falta añadir la ruta en la que trabajaremos a través de un método dinámico
>>>>>>> 6474913708615fe1e9aca2c67e8bfcc8f0369eef
    }
}

package com.jesi.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.jesi.springboot.di.app.springboot_di.models.Product;
import com.jesi.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.jesi.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpat:json/product.json")
    private Resource resource;
    @Bean("productJson")
    //@Primary
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
    
}

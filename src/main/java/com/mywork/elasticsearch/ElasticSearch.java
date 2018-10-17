package com.mywork.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Manikanta Kandagatla
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.mywork.elasticsearch")
public class ElasticSearch
{ 
    public static void main(String[] args)
    {
        SpringApplication.run(ElasticSearch.class, args);
    }
}

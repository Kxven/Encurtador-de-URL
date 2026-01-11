package com.encurtador.urlshortener.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

import jakarta.annotation.PostConstruct;

import java.time.Duration;

@Configuration
public class MongoConfig{

    private final MongoTemplate mongoTemplate;

    public MongoConfig( MongoTemplate mongoTemplate ){
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void createTTLIndex(){
        IndexOperations ops = mongoTemplate.indexOps("urls");

        Index index = new Index()
                .on("expiresAt",Sort.Direction.ASC)
                .expire(Duration.ZERO);

        ops.createIndex(index);
    }
}



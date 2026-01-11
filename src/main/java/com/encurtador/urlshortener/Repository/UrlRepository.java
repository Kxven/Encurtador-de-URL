package com.encurtador.urlshortener.Repository;

import com.encurtador.urlshortener.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String>{
}

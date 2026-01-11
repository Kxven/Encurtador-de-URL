package com.encurtador.urlshortener.controller;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.encurtador.urlshortener.Repository.UrlRepository;
import com.encurtador.urlshortener.controller.DTO.ShortenUrlRequest;
import com.encurtador.urlshortener.controller.DTO.ShortenUrlResponse;
import com.encurtador.urlshortener.entities.UrlEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Date;

@RestController
public class UrlController{

    private final UrlRepository urlRepository;

    public UrlController( UrlRepository repository ){
        this.urlRepository = repository;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl( @RequestBody ShortenUrlRequest request,
                                           HttpServletRequest servletRequest ){

        String id;
        do {
             id = NanoIdUtils.randomNanoId(
                    new SecureRandom(),
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray(),
                    5 + new SecureRandom().nextInt((10 - 5) + 1)
            );
        }while(urlRepository.existsById(id));

        urlRepository.save(
                new UrlEntity(
                        id,
                        request.url(),
                        new Date(System.currentTimeMillis() + 60_000)
                )
        );
        var redirectUrl = servletRequest
                .getRequestURL()
                .toString()
                .replace("/shorten-url", "/" + id);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }
}

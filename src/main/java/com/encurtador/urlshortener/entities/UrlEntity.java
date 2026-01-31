package com.encurtador.urlshortener.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "urls")
public class UrlEntity{

    @Id
    private String id;

    private String fullUrl;

    private Date expiresAt;

    public UrlEntity(){
    }

    public UrlEntity( String id,String fullUrl,Date expiresAt ){
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAt = expiresAt;
    }

    public String getId(){
        return id;
    }

    public void setId( String id ){
        this.id = id;
    }

    public String getFullUrl(){
        return fullUrl;
    }

    public void setFullUrl( String fullUrl ){
        this.fullUrl = fullUrl;
    }

    public Date getExpiresAt(){
        return expiresAt;
    }

    public void setExpiresAt( Date expiresAt ){
        this.expiresAt = expiresAt;
    }
}

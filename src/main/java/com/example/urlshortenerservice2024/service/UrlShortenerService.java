package com.example.urlshortenerservice2024.service;

import com.example.urlshortenerservice2024.entity.UrlMapping;
import com.example.urlshortenerservice2024.repository.UrlMappingRepository;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlShortenerService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(longUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMappingRepository.save(urlMapping);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            System.out.println("Found long URL: " + urlMapping.getLongUrl());
            return urlMapping.getLongUrl();
        } else {
            System.out.println("URL not found for short URL: " + shortUrl);
            return null;
        }
    }

    private String generateShortUrl() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 7);
    }
}



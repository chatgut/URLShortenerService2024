package com.example.urlshortenerservice2024.service;

import com.example.urlshortenerservice2024.entity.UrlMapping;
import com.example.urlshortenerservice2024.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UrlShortenerService {

    @Autowired
    private static UrlMappingRepository urlMappingRepository;

    public static String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(longUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMappingRepository.save(urlMapping);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        return urlMapping != null ? urlMapping.getLongUrl() : null;
    }

    private static String generateShortUrl() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 7);
    }
}


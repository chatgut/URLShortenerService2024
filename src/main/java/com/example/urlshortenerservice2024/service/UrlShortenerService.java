package com.example.urlshortenerservice2024.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UrlShortenerService {

    private final Map<String, String> urlMap = new HashMap<>();

    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl();
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    private String generateShortUrl() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 7);
    }
}

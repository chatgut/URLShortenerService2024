package com.example.urlshortenerservice2024.Controller;

import com.example.urlshortenerservice2024.dto.UrlRequest;
import com.example.urlshortenerservice2024.service.UrlShortenerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UrlShortenerController {

    private final Map<String, String> urlMap = new HashMap<>();

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest urlRequest) {
        String longUrl = urlRequest.getLongUrl();
        if (longUrl == null || longUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }
        String shortUrl = UrlShortenerService.shortenUrl(longUrl);
        return ResponseEntity.ok(shortUrl);
    }

    private static String generateShortUrl() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 7);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable String shortUrl) {
        String longUrl = urlMap.get(shortUrl);
        if (longUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, longUrl).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shortened URL not found");
        }
    }
}







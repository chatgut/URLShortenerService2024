package com.example.urlshortenerservice2024.Controller;

import com.example.urlshortenerservice2024.dto.UrlRequest;
import com.example.urlshortenerservice2024.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest urlRequest) {
        if (urlRequest.getLongUrl() == null || urlRequest.getLongUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid URL provided");
        }

        String shortUrl = urlShortenerService.shortenUrl(urlRequest.getLongUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrl) {
        String longUrl = urlShortenerService.getLongUrl(shortUrl);
        if (longUrl != null) {
            return ResponseEntity.status(302)
                    .header("Location", longUrl)
                    .build();
        } else {
            return ResponseEntity.status(404).body("Shortened URL not found");
        }
    }
}






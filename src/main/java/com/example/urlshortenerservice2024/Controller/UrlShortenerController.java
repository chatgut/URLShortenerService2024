package com.example.urlshortenerservice2024.Controller;

import com.example.urlshortenerservice2024.dto.UrlRequest;
import com.example.urlshortenerservice2024.dto.UrlResponse;
import com.example.urlshortenerservice2024.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest urlRequest) {
        String longUrl = urlRequest.getLongUrl();
        if (longUrl == null || longUrl.isEmpty()) {
            return ResponseEntity.badRequest().body(new UrlResponse(null, null, "Invalid URL"));
        }

        String shortUrl = UrlShortenerService.shortenUrl(longUrl);
        UrlResponse response = new UrlResponse(longUrl, shortUrl, "URL successfully shortened");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable String shortUrl) {
        String longUrl = urlShortenerService.getLongUrl(shortUrl);
        if (longUrl != null) {
            return ResponseEntity.status(302).header("Location", longUrl).build();
        } else {
            return ResponseEntity.status(404).body("Shortened URL not found");
        }
    }
}








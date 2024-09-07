package com.example.urlshortenerservice2024.Controller;

import com.example.urlshortenerservice2024.dto.UrlRequest;
import com.example.urlshortenerservice2024.dto.UrlResponse;
import com.example.urlshortenerservice2024.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
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
        try {
            String longUrl = urlRequest.getLongUrl();
            if (longUrl == null || longUrl.isEmpty()) {
                return ResponseEntity.badRequest().body(new UrlResponse(null, null, "Invalid URL"));
            }

            String shortUrl = urlShortenerService.shortenUrl(longUrl);
            UrlResponse response = new UrlResponse(longUrl, shortUrl, "URL successfully shortened");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UrlResponse(null, null, "Internal Server Error"));
        }
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable String shortUrl) {
        try {
            String longUrl = urlShortenerService.getLongUrl(shortUrl);
            if (longUrl != null) {
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", longUrl).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shortened URL not found");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}









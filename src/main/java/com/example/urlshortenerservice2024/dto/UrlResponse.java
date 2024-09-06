package com.example.urlshortenerservice2024.dto;

public class UrlResponse {
    private String longUrl;
    private String shortUrl;
    private String message;

    public UrlResponse(String longUrl, String shortUrl, String message) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.message = message;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

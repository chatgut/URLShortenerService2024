package com.example.urlshortenerservice2024.repository;

import com.example.urlshortenerservice2024.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortUrl(String shortUrl);
}

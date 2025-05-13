
package com.roadmap.url_shortner.service;

import com.roadmap.url_shortner.model.UrlMapping;
import com.roadmap.url_shortner.repository.UrlMappingRepository;
import com.roadmap.url_shortner.util.GenerateShortCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for managing URL mappings and short codes.
 */
@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final UrlMappingRepository repository;
    private final GenerateShortCode gsc;
    private final SecureRandom random = new SecureRandom();

    /**
     * Creates a short code and stores the mapping.
     */
    @Transactional
    public UrlMapping createShortUrl(String originalUrl) {
        String shortCode = gsc.generateUniqueShortCode();
        UrlMapping urlMapping = UrlMapping.builder()
                .shortCode(shortCode)
                .url(originalUrl)
                .accessCount(0)
                .build();
        return repository.save(urlMapping);
    }

    /**
     * Fetches the original URL and increments the access count.
     */
    @Transactional
    public Optional<UrlMapping> getOriginalUrl(String shortCode) {
        return repository.findById(shortCode).map(mapping -> {
            mapping.setAccessCount(mapping.getAccessCount() + 1);
            return repository.save(mapping);
        });
    }

    /**
     * Fetches statistics of a given short code.
     */
    @Transactional(readOnly = true)
    public Optional<UrlMapping> getUrlStats(String shortCode) {
        return repository.findById(shortCode);
    }

    /**
     * Updates the original URL for the given short code.
     */
    @Transactional
    public Optional<UrlMapping> updateShortUrl(String shortCode, String newUrl) {
        return repository.findById(shortCode).map(mapping -> {
            mapping.setUrl(newUrl);
            return repository.save(mapping);
        });
    }

    /**
     * Deletes the URL mapping by short code.
     */
    @Transactional
    public boolean deleteShortUrl(String shortCode) {
        if (repository.existsById(shortCode)) {
            repository.deleteById(shortCode);
            return true;
        }
        return false;
    }

}

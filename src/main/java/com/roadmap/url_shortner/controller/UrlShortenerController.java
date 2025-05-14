
package com.roadmap.url_shortner.controller;

import com.roadmap.url_shortner.model.UrlMapping;
import com.roadmap.url_shortner.service.UrlShortenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.roadmap.url_shortner.util.*;

/**
 * Controller for handling HTTP requests related to URL shortening.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService service;
    private final UrlShortnerMapping util;

    /**
     * Creates a new short URL from the provided original URL.
     */
    @PostMapping("/shorten")
    public ResponseEntity<?> handleCreateShortUrl(@Valid @RequestBody UrlMapping urlMapping, BindingResult result) {
        UrlMapping saved = service.createShortUrl(urlMapping.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(util.toDto(saved));
    }

    /**
     * Returns the original URL as JSON for the given short code.
     */
    @GetMapping("/shorten/{shortCode}")
    public ResponseEntity<?> handleGetOriginalUrlAsJson(@PathVariable String shortCode) {
        return service.getOriginalUrl(shortCode)
                .map(mapping -> ResponseEntity.ok(util.toDto(mapping)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Redirects the user to the original URL using the short code.
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> handleRedirectToOriginalUrl(@PathVariable String shortCode) {
        return service.getOriginalUrl(shortCode)
                .map(mapping -> ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", mapping.getUrl())
                        .build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates the original URL associated with the given short code.
     */
    @PutMapping("/shorten/{shortCode}")
    public ResponseEntity<?> handleUpdateShortUrl(@PathVariable String shortCode, @Valid @RequestBody UrlMapping urlMapping, BindingResult result) {
        return service.updateShortUrl(shortCode, urlMapping.getUrl())
                .map(updated -> ResponseEntity.ok(util.toDto(updated)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes the short URL entry associated with the given short code.
     */
    @DeleteMapping("/shorten/{shortCode}")
    public ResponseEntity<Void> handleDeleteShortUrl(@PathVariable String shortCode) {
        return service.deleteShortUrl(shortCode) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    /**
     * Returns statistics (access count) for the given short code.
     */
    @GetMapping("/shorten/{shortCode}/stats")
    public ResponseEntity<?> handleGetShortUrlStats(@PathVariable String shortCode) {
        return service.getUrlStats(shortCode)
                .map(mapping -> ResponseEntity.ok(util.toStatsDto(mapping)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}

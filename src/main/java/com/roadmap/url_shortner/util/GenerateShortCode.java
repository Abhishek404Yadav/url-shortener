package com.roadmap.url_shortner.util;

import com.roadmap.url_shortner.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;

@Component
public class GenerateShortCode {

    @Autowired
    private UrlMappingRepository repository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private static final int MAX_ATTEMPTS = 10;
    private final SecureRandom random = new SecureRandom();

    public String generateUniqueShortCode() {
        for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
            String shortCode = random.ints(SHORT_CODE_LENGTH, 0, CHARACTERS.length())
                    .mapToObj(i -> String.valueOf(CHARACTERS.charAt(i)))
                    .collect(Collectors.joining());

            if (!repository.existsById(shortCode)) {
                return shortCode;
            }
        }
        throw new RuntimeException("Failed to generate a unique short code after " + MAX_ATTEMPTS + " attempts");
    }
}

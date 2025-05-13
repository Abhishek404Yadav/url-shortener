package com.roadmap.url_shortner.util;

import com.roadmap.url_shortner.model.UrlMapping;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UrlShortnerUtil {
    // Helper method to map UrlMapping entity to a DTO response.
    public Map<String, Object> toDto(UrlMapping mapping) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("shortCode", mapping.getShortCode());
        dto.put("url", mapping.getUrl());
        dto.put("createdAt", mapping.getCreatedAt());
        dto.put("updatedAt", mapping.getUpdatedAt());
        return dto;
    }

    // Formats field-level validation errors into a simple key-value map.
    public Map<String, String> formatErrors(BindingResult result) {
        return result.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
    }
}

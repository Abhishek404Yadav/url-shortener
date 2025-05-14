package com.roadmap.url_shortner.util;

import com.roadmap.url_shortner.Dto.UrlMappingDto;
import com.roadmap.url_shortner.Dto.UrlStatsDto;
import com.roadmap.url_shortner.model.UrlMapping;
import org.springframework.stereotype.Component;

@Component
public class UrlShortnerMapping {
    // Helper method to map UrlMapping entity to a DTO response.
    public UrlMappingDto toDto(UrlMapping mapping) {
        return new UrlMappingDto(
                mapping.getShortCode(),
                mapping.getUrl(),
                mapping.getCreatedAt(),
                mapping.getUpdatedAt()
        );
    }
    public UrlStatsDto toStatsDto(UrlMapping mapping) {
        return new UrlStatsDto(
                mapping.getShortCode(),
                mapping.getUrl(),
                mapping.getCreatedAt(),
                mapping.getUpdatedAt(),
                mapping.getAccessCount()
        );
    }

}

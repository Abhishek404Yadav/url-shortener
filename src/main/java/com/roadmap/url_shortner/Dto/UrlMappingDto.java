package com.roadmap.url_shortner.Dto;

import java.time.LocalDateTime;

public record UrlMappingDto (

    String shortCode,
    String url,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){}

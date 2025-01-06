package com.example.testspringboot.dto;

import java.util.List;

public record FilmResponseDto(Long id, String titre, String description, List<ActeurResponseDto> acteurs) {
}

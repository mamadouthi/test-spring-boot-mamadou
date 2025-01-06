package com.example.testspringboot.dto;

import java.util.List;

public record FilmRequestDto(String titre, String description, List<ActeurRequestDto> acteurs) {
}

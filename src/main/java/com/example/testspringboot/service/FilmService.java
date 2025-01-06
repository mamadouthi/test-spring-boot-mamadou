package com.example.testspringboot.service;

import com.example.testspringboot.dto.FilmRequestDto;
import com.example.testspringboot.dto.FilmResponseDto;

public interface FilmService {

    FilmResponseDto getFilm (Long id);

    FilmResponseDto createFilm(FilmRequestDto film);
}

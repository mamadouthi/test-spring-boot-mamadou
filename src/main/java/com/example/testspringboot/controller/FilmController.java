package com.example.testspringboot.controller;

import com.example.testspringboot.dto.FilmRequestDto;
import com.example.testspringboot.dto.FilmResponseDto;
import com.example.testspringboot.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/film")
@RequiredArgsConstructor
public class FilmController {
    private static final Logger LOGGER = Logger.getLogger(FilmController.class.getName());

    private final FilmService filmService;

    @GetMapping(path = "{id}")
    public ResponseEntity<FilmResponseDto> getFilm(@PathVariable("id") String id) {
        LOGGER.info(String.format("Getting film (id=%s)", id));
        var filmDto = filmService.getFilm(Long.parseLong(id));

        return ResponseEntity.ok().body(filmDto);
    }

    @PostMapping
    public ResponseEntity<FilmResponseDto> addNewFilm(@RequestBody FilmRequestDto filmRequestDto) {
        LOGGER.info("Adding new film");
        var savedFilmDto = filmService.createFilm(filmRequestDto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedFilmDto.id())
                .toUri();

        return ResponseEntity.created(location).body(savedFilmDto);
    }
}

package com.example.testspringboot.controller;

import com.example.testspringboot.dto.FilmResponseDto;
import com.example.testspringboot.exception.ControllerExceptionHandler;
import com.example.testspringboot.service.FilmService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FilmControllerTest {

    private static final Long FILM_ID = 10L;
    private static final String TITRE = "Star Wars: The Empire Strikes Back";
    private static final String DESCRIPTION = "Darth Vader is adamant about turning Luke Skywalker to the dark side.";

    private MockMvc mockMvc;

    private FilmResponseDto filmResponseDto;

    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filmResponseDto = new FilmResponseDto(FILM_ID, TITRE, DESCRIPTION, List.of());
        mockMvc = MockMvcBuilders
                .standaloneSetup(filmController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void givenRequestWithValidFilmId_shouldSucceedWith200() throws Exception {
        // given

        // when
        when(filmService.getFilm(FILM_ID)).thenReturn(filmResponseDto);

        // then
        mockMvc.perform(get("/api/film/{id}", FILM_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(FILM_ID))
                .andExpect(jsonPath("$.titre").value(TITRE))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    void givenRequestWithInValidFilmId_shouldFailWith404() throws Exception {
        // given
        var invalidFilmId = 999L;

        // when
        when(filmService.getFilm(invalidFilmId)).thenThrow(new EntityNotFoundException("Film not found"));

        // then
        mockMvc.perform(get("/api/film/{id}", invalidFilmId))
                .andExpect(status().isNotFound());
    }

    //TODO Add missing test for addNewFilm() and Cucumber tests
}
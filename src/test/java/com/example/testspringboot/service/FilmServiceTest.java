package com.example.testspringboot.service;

import com.example.testspringboot.dto.ActeurRequestDto;
import com.example.testspringboot.dto.ActeurResponseDto;
import com.example.testspringboot.dto.FilmRequestDto;
import com.example.testspringboot.dto.FilmResponseDto;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.model.Acteur;
import com.example.testspringboot.model.Film;
import com.example.testspringboot.repository.FilmRepository;
import com.example.testspringboot.service.impl.FilmServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    private static final Long FILM_ID = 10L;
    private static final String TITRE = "Star Wars: The Empire Strikes Back";
    private static final String DESCRIPTION = "Darth Vader is adamant about turning Luke Skywalker to the dark side.";

    private static final Long ACTEUR_ID = 11L;
    private static final String PRENOM = "Harrison";
    private static final String NOM = "Ford";

    private static final Optional<Film> NO_FILM = Optional.empty();

    private FilmService filmService;

    private FilmMapper filmMapper;

    @Mock
    private FilmRepository filmRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        filmMapper = Mappers.getMapper(FilmMapper.class);
        filmService = new FilmServiceImpl(filmRepository, filmMapper);
    }

    @Test
    void givenFoundFilm_whenGetFilm_thenReturnsFilm() {
        // given
        var acteur = Acteur.builder().id(ACTEUR_ID).nom(NOM).prenom(PRENOM).build();
        var film = Film.builder().id(FILM_ID).titre(TITRE).description(DESCRIPTION).acteurs(List.of(acteur)).build();
        given(filmRepository.findById(FILM_ID)).willReturn(Optional.of(film));

        // when
        var foundFilmResponseDto = filmService.getFilm(FILM_ID);

        // then
        var expectedActeurResponseDto = new ActeurResponseDto(ACTEUR_ID, NOM, PRENOM);
        var exepectedFilmResponseDto = new FilmResponseDto(FILM_ID, TITRE, DESCRIPTION, List.of(expectedActeurResponseDto));
        assertThat(foundFilmResponseDto).isEqualTo(exepectedFilmResponseDto);
    }


    @Test
    void givenNotFoundFilm_whenGetFilm_thenThrowsEntityNotFoundException() {
        // given
        given(filmRepository.findById(FILM_ID)).willReturn(NO_FILM);

        // when
        Executable exec = () -> filmService.getFilm(FILM_ID);

        // then
        assertThrows(EntityNotFoundException.class, exec);
    }


    @Test
    void givenFilm_whenCreateFilm_thenVerifyFilmIsSavedInDbAndReturnedWithGeneratedIds() {
        // given
        var acteurRequestDto =  new ActeurRequestDto(NOM, PRENOM);
        var filmRequestDto = new FilmRequestDto(TITRE, DESCRIPTION, List.of(acteurRequestDto));

        var acteur = Acteur.builder().nom(NOM).prenom(PRENOM).build();
        var film = Film.builder().titre(TITRE).description(DESCRIPTION).acteurs(List.of(acteur)).build();

        var savedActeurWithId = Acteur.builder().id(ACTEUR_ID).nom(NOM).prenom(PRENOM).build();
        var savedfilmWithId = Film.builder().id(FILM_ID).titre(TITRE).description(DESCRIPTION).acteurs(List.of(savedActeurWithId)).build();

        var expectedActeurResponseDto = new ActeurResponseDto(ACTEUR_ID, NOM, PRENOM);
        var exepectedFilmResponseDto = new FilmResponseDto(FILM_ID, TITRE, DESCRIPTION, List.of(expectedActeurResponseDto));

        given(filmRepository.save(film)).willReturn(savedfilmWithId);

        // when
        var actualFilmResponseDto = filmService.createFilm(filmRequestDto);

        // then
        verify(filmRepository).save(film);
        assertThat(actualFilmResponseDto).isEqualTo(exepectedFilmResponseDto);
    }
}
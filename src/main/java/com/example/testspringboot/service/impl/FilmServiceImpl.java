package com.example.testspringboot.service.impl;

import com.example.testspringboot.dto.FilmRequestDto;
import com.example.testspringboot.dto.FilmResponseDto;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import com.example.testspringboot.service.FilmService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    @Override
    public FilmResponseDto getFilm(Long id) {
        return filmRepository.findById(id).map(filmMapper::modelToDto).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FilmResponseDto createFilm(FilmRequestDto filmRequestDto) {
        var film = filmMapper.dtoToModel(filmRequestDto);
        var savedFilm = filmRepository.save(film);
        return filmMapper.modelToDto(savedFilm);
    }
}

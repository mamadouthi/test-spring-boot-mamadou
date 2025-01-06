package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.FilmRequestDto;
import com.example.testspringboot.dto.FilmResponseDto;
import com.example.testspringboot.model.Film;
import org.mapstruct.Mapper;

@Mapper
public interface FilmMapper {

    FilmResponseDto modelToDto(Film film);

    Film dtoToModel(FilmRequestDto filmRequestDto);
}

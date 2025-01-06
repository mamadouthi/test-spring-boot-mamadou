package com.example.testspringboot.repository;

import com.example.testspringboot.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}

package com.volomak.movieland.service;

import com.volomak.movieland.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(Long id);

    List<Genre> getByMovieId(Long id);

    List<Long> getIdsByMovieId(Long id);

    List<Genre> getGenres();
}
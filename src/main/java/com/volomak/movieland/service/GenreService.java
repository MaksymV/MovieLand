package com.volomak.movieland.service;

import com.volomak.movieland.entity.Genre;

import java.util.List;

public interface GenreService {
    public Genre getById(Long id);

    List<Genre> getByMovieId(Long id);

    List<Genre> getIdsByMovieId(Long id);

    public List<Genre> getGenres();
}
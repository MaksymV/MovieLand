package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(Long id);

    List<Genre> getByMovieId(Long id);

    List<Long> getIdsByMovieId(Long id);

    List<Genre> getGenres();
}

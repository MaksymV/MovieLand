package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(int id);

    List<Genre> getByMovieId(int id);

    void add(Genre genre);
}

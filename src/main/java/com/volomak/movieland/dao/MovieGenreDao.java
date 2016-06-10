package com.volomak.movieland.dao;

import com.volomak.movieland.entity.MovieGenre;

public interface MovieGenreDao {
    MovieGenre getById(int id);

    void add(MovieGenre movieGenre);
}

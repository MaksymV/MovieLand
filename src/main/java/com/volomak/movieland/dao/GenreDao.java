package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Genre;

public interface GenreDao {
    Genre getById(int id);

    void add(Genre genre);
}

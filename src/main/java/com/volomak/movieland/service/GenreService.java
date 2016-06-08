package com.volomak.movieland.service;

import com.volomak.movieland.entity.Genre;

public interface GenreService {
    Genre getById(int id);

    void add(Genre genre);
}
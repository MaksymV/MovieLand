package com.volomak.movieland.dao;

import com.volomak.movieland.entity.MovieCountry;

public interface MovieCountryDao {
    MovieCountry getById(int id);

    void add(MovieCountry movieCountry);
}

package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    Country getById(Long id);

    List<Country> getByMovieId(Long id);
}

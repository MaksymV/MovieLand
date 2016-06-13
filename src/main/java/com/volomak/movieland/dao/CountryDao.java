package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    Country getById(int id);

    List<Country> getByMovieId(int id);

    void add(Country country);
}

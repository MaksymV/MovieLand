package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getByMovieId(Long id);
}

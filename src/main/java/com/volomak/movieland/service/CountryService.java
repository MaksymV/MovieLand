package com.volomak.movieland.service;

import com.volomak.movieland.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> getByMovieId(Long id);
}

package com.volomak.movieland.service;

import com.volomak.movieland.entity.Country;
import com.volomak.movieland.service.dto.CountryListDto;

import java.util.List;

public interface CountryService {
    Country getById(int id);

    List<CountryListDto> getByMovieId(int id);

    void add(Country country);
}

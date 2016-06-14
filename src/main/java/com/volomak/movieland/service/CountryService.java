package com.volomak.movieland.service;

import com.volomak.movieland.entity.Country;
import com.volomak.movieland.service.dto.CountryListDto;

import java.util.List;

public interface CountryService {
    Country getById(Long id);

    List<CountryListDto> getByMovieId(Long id);
}

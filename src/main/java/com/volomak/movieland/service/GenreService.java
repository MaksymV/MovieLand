package com.volomak.movieland.service;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.dto.GenreListDto;

import java.util.List;

public interface GenreService {
    Genre getById(Long id);

    List<GenreListDto> getByMovieId(Long id);
}
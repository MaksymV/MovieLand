package com.volomak.movieland.service;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.dto.GenreListDto;

import java.util.List;

public interface GenreService {
    Genre getById(int id);

    List<GenreListDto> getByMovieId(int id);

    void add(Genre genre);
}
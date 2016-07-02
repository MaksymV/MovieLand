package com.volomak.movieland.service.cache;

import com.volomak.movieland.entity.Genre;

import java.util.List;

public interface GenreCache {
    List<Genre> getGenres(List<Long> genreIds);
}

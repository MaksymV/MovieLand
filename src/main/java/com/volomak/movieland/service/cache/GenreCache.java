package com.volomak.movieland.service.cache;

import com.volomak.movieland.entity.Genre;

import java.util.List;
import java.util.Map;

public interface GenreCache {
    Map<Long, Genre> getGenres();
}

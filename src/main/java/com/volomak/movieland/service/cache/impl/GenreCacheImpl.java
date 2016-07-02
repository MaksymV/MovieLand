package com.volomak.movieland.service.cache.impl;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.service.cache.GenreCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class GenreCacheImpl implements GenreCache {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final int FIXED_DELAY = 4*60*60*1000;
    private Map<Long, Genre> genreMap = new ConcurrentHashMap<>();

    @Autowired
    private GenreService genreService;

    @Override
    public List<Genre> getGenres(List<Long> genreIds){
        return genreMap.values().stream()
                .filter(p -> genreIds.stream().anyMatch(i -> i == p.getId()))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedDelay = FIXED_DELAY)
    public void cacheUpdate(){
        log.info("Start refresh genre cache");
        long startTime = System.currentTimeMillis();
        List<Genre> genres = genreService.getGenres();
        genreMap = genres.stream().collect(Collectors.toMap(Genre::getId, genre -> genre));
        log.info("Finish refresh genre cache. It took {} ms", System.currentTimeMillis() - startTime);
    }
}

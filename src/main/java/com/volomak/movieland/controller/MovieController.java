package com.volomak.movieland.controller;

import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.util.JsonJacksonConverter;
import com.volomak.movieland.util.JsonManualConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/movie")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonManualConverter jsonManualConverter;

    @Autowired
    private JsonJacksonConverter jsonJacksonConverter;

    @RequestMapping(value = "/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId){
        log.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        Movie movie = movieService.getById(movieId);
        String movieJson = jsonManualConverter.toJson(movie);
        log.info("Movie {} is received. It took {} ms", movieJson, System.currentTimeMillis() - startTime);
        return movieJson;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addMovie(@RequestBody String json){
        log.info("Sending request to add new movie {}", json);
        long startTime = System.currentTimeMillis();
        try {
            Movie movie = jsonJacksonConverter.parseMovie(json);
            movieService.add(movie);
        } catch (Exception e) {
            log.error("Exception occurred during adding the movie", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Movie {} is added. It took {} ms", json, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

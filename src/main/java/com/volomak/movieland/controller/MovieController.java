package com.volomak.movieland.controller;

import com.google.gson.Gson;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable Long movieId){
        log.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        MovieDetailsDto movie = movieService.getById(movieId);
        String movieJson = new Gson().toJson(movie);
        log.info("Movie {} is received. It took {} ms", movieJson, System.currentTimeMillis() - startTime);
        return movieJson;
    }

    @RequestMapping(value = "/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovies(){
        log.info("Sending request to get movies");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movies = movieService.getMovies();
        String movieJson = new Gson().toJson(movies);
        log.info("Movies are received. It took {} ms", System.currentTimeMillis() - startTime);
        return movieJson;
    }
}

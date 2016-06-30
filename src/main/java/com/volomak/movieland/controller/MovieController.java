package com.volomak.movieland.controller;

import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import com.volomak.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/v1/movie")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable Long movieId){
        log.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        MovieDetailsDto movie = movieService.getById(movieId);
        String movieJson = jsonConverter.toJson(movie);
        log.info("Movie {} is received. It took {} ms", movieJson, System.currentTimeMillis() - startTime);
        return movieJson;
    }

    @RequestMapping(value = "/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovies(@RequestParam(value = "rating", required = false) String ratingOrder
                           ,@RequestParam(value = "price", required = false) String priceOrder
                           ,@RequestParam(value = "page", defaultValue = "1") int page){
        log.info("Sending request to get movies");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movies = movieService.getMovies(ratingOrder, priceOrder, page);
        String movieJson = jsonConverter.toJson(movies);
        log.info("Movies are received. It took {} ms", System.currentTimeMillis() - startTime);
        return movieJson;
    }

    @RequestMapping(value="/search", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestBody MovieSearchRequestDto movieSearchRequestDto){
        log.info("Sending request to find movies");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movies = movieService.search(movieSearchRequestDto);
        String movieJson = jsonConverter.toJson(movies);
        log.info("Movies are found. It took {} ms", System.currentTimeMillis() - startTime);
        return movieJson;
    }

    @RequestMapping(value="/search/default", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchDefault(){
        log.info("Sending request to find movies");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movies = movieService.searchDefault();
        String movieJson = jsonConverter.toJson(movies);
        log.info("Movies are found. It took {} ms", System.currentTimeMillis() - startTime);
        return movieJson;
    }


}

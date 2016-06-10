package com.volomak.movieland.controller;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.util.JsonJacksonConverter;
import com.volomak.movieland.util.JsonManualConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ptcvas17 on 08.06.2016.
 */

@Controller
@RequestMapping("/v1/genre")
public class GenreController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    @Autowired
    private JsonManualConverter jsonManualConverter;

    @Autowired
    private JsonJacksonConverter jsonJacksonConverter;

    @RequestMapping(value = "/{genreId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getGenreById(@PathVariable int genreId){
        log.info("Sending request to get city with id = {}", genreId);
        long startTime = System.currentTimeMillis();
        Genre genre = genreService.getById(genreId);
        String genreJson = jsonManualConverter.toJson(genre);
        log.info("City {} is received. It took {} ms", genreJson, System.currentTimeMillis() - startTime);
        return genreJson;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addGenre(@RequestBody String json){
        log.info("Sending request to add new city {}", json);
        long startTime = System.currentTimeMillis();
        try {
        Genre genre = jsonJacksonConverter.parseGenre(json);
        genreService.add(genre);
        } catch (Exception e) {
            log.error("Exception occurred during adding the city", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("City {} is added. It took {} ms", json, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

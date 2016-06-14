package com.volomak.movieland.controller;

import com.google.gson.Gson;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ptcvas17 on 08.06.2016.
 */

@Controller
public class GenreController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    @RequestMapping(value = "genre/{genreId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getGenreById(@PathVariable Long genreId){
        log.info("Sending request to get city with id = {}", genreId);
        long startTime = System.currentTimeMillis();
        Genre genre = genreService.getById(genreId);
        String genreJson = new Gson().toJson(genre);
        log.info("City {} is received. It took {} ms", genreJson, System.currentTimeMillis() - startTime);
        return genreJson;
    }
}

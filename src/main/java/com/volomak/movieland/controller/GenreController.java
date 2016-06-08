package com.volomak.movieland.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.volomak.movieland.service.GenreService;

/**
 * Created by ptcvas17 on 08.06.2016.
 */
public class GenreController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService GentreService;


}

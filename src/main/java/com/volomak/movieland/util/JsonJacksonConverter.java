package com.volomak.movieland.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.volomak.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonJacksonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
//??
    private ObjectMapper objectMapper = new ObjectMapper();

    public Genre parseGenre(String json){
        log.info("Start Genre json parse");
        long startTime = System.currentTimeMillis();
        Genre genre = parseValue(json, Genre.class);
        long time = System.currentTimeMillis() - startTime;
        log.info("Genre {} is received. It took {} ms", genre, time);
        return genre;
    }

    private <T> T parseValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

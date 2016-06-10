package com.volomak.movieland.util;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JsonManualConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String COMMA_SEPARATOR = ",";
    private static final String COLON_SEPARATOR = ":";

    public String toJson(Genre genre){
        log.info("Start converting genre {} to json", genre);
        StringBuilder json = new StringBuilder("{");
        String[] genreFieldNames = {"id", "name_c"};
        Object[] genreFields = { genre.getId() , genre.getName() };
        for (int i = 0; i < genreFieldNames.length; i++) {
            json.append(surroundByQuotes(genreFieldNames[i]));
            json.append(COLON_SEPARATOR);
            json.append(surroundByQuotes(genreFields[i]));
            if (i != genreFieldNames.length -1){
                json.append(COMMA_SEPARATOR);
            }


        }
        json.append("}");
        log.info("Receiving city as json {}", json);
        return json.toString();
    }

    public String toJson(Movie movie){
        log.info("Start converting movie {} to json", movie);
        StringBuilder json = new StringBuilder("{");
        String[] genreFieldNames = {"id", "name", "original_name", "year_i", "description_c", "rate_r", "price_r"};
        Object[] genreFields = {movie.getId() , movie.getName(), movie.getOriginalName(), movie.getYear(), movie.getDescription(), movie.getRate(), movie.getPrice()};
        for (int i = 0; i < genreFieldNames.length; i++) {
            json.append(surroundByQuotes(genreFieldNames[i]));
            json.append(COLON_SEPARATOR);
            json.append(surroundByQuotes(genreFields[i]));
            if (i != genreFieldNames.length -1){
                json.append(COMMA_SEPARATOR);
            }


        }
        json.append("}");
        log.info("Receiving city as json {}", json);
        return json.toString();
    }

    private String surroundByQuotes(Object value) {
        return "\"" + value + "\"";
    }
}

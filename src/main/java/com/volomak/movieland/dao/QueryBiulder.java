package com.volomak.movieland.dao;

import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import org.springframework.stereotype.Service;

@Service
public class QueryBiulder {
    public String movieSearch(MovieSearchRequestDto movieSearchRequestDto){
        StringBuilder stringBuilderSelect = new StringBuilder();
        StringBuilder stringBuilderWhere = new StringBuilder();

        stringBuilderSelect.append("SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie \n");

        if (movieSearchRequestDto.getName() != null){
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") +  "movie.name like '%%%s%%' \n");
        };

        if (movieSearchRequestDto.getOriginalName() != null){
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "movie.original_name like '%%%s%%' \n");
        };

        if (movieSearchRequestDto.getYear() != 0){
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "movie.year_i = %d \n");
        };

        if (movieSearchRequestDto.getCountry() != null){
            stringBuilderSelect.append("inner join movie_country on (movie_country.movie_id = movie.id) inner join country on (country.id = movie_country.country_id) \n");
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "country.name like '%%%s%%' \n");
        };

        if (movieSearchRequestDto.getGenre() != null){
            stringBuilderSelect.append("inner join movie_genre on (movie_genre.movie_id  = movie.id) inner join genre on (genre.id = movie_genre.genre_id) \n");
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "genre.name_c like '%%%s%%' \n");
        };

        if (stringBuilderWhere.length() != 0){
            stringBuilderWhere.insert(0, "where ");
        }

        return stringBuilderSelect.append(stringBuilderWhere).toString();
    }
}

package com.volomak.movieland.dao;

import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryBiulder {
    public String movieSearch(MovieSearchRequestDto movieSearchRequestDto){
        StringBuilder stringBuilderSelect = new StringBuilder();
        StringBuilder stringBuilderWhere = new StringBuilder();
        List<Object> variables = new ArrayList<>();

        stringBuilderSelect.append("SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie\n");

        if (movieSearchRequestDto.getName() != null){
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") +  "movie.name like '%%%s%%'\n");
            variables.add(movieSearchRequestDto.getName());
        };

        if (movieSearchRequestDto.getOriginalName() != null){
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "movie.original_name like '%%%s%%'\n");
            variables.add(movieSearchRequestDto.getOriginalName());
        };

        if (movieSearchRequestDto.getYear() != 0){
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "movie.year_i = %d\n");
            variables.add(movieSearchRequestDto.getYear());
        };

        if (movieSearchRequestDto.getCountry() != null){
            stringBuilderSelect.append("inner join movie_country on (movie_country.movie_id = movie.id) inner join country on (country.id = movie_country.country_id)\n");
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "country.name like '%%%s%%'\n");
            variables.add(movieSearchRequestDto.getCountry().getName());
        };

        if (movieSearchRequestDto.getGenre() != null){
            stringBuilderSelect.append("inner join movie_genre on (movie_genre.movie_id  = movie.id) inner join genre on (genre.id = movie_genre.genre_id)\n");
            stringBuilderWhere.append((stringBuilderWhere.length() == 0 ? "" : "or ") + "genre.name_c like '%%%s%%'\n");
            variables.add(movieSearchRequestDto.getGenre().getName());
        };

        if (stringBuilderWhere.length() != 0){
            stringBuilderWhere.insert(0, "where ");
        }

        return String.format(stringBuilderSelect.append(stringBuilderWhere).toString(), variables.toArray());
    }

    public String getMovies(String ratingOrder, String priceOrder, int fromIndex, int toIndex){
        StringBuilder stringBuilderSelect = new StringBuilder();
        StringBuilder stringBuilderOrder = new StringBuilder();
        StringBuilder stringBuilderWhere = new StringBuilder();
        List<Object> variables = new ArrayList<>();

        stringBuilderSelect.append("SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie\n");
        stringBuilderWhere.append("WHERE rownum between %d and %d ");
        variables.add(fromIndex);
        variables.add(toIndex);

        if (ratingOrder != null){
            stringBuilderOrder.append(" rate %s ");
            variables.add(ratingOrder);
        }
        if (priceOrder != null){
            stringBuilderOrder.append(" price %s ");
            variables.add(priceOrder);
        }
        if (stringBuilderOrder.length() != 0){
            stringBuilderOrder.insert(0, " order by ");
        }
        return String.format(stringBuilderSelect.append(stringBuilderWhere).append(stringBuilderOrder).toString(), variables);
    }
}

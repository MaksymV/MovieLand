package com.volomak.movieland.dao.postgres.util;

import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import com.volomak.movieland.util.Constant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryBiulder {

    public static final String SELECT_FROM_MOVIE = "SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie\n";
    public static final String WHERE_NAME = "movie.name like '%%%s%%'\n";
    public static final String WHERE_ORIGINAL_NAME = "movie.original_name like '%%%s%%'\n";
    public static final String WHERE_YEAR = "movie.year_i = %d\n";
    public static final String WHERE_COUNTRY = "country.name like '%%%s%%'\n";
    public static final String JOIN_COUNTRY = "inner join movie_country on (movie_country.movie_id = movie.id) inner join country on (country.id = movie_country.country_id)\n";
    public static final String WHERE_GENRE = "genre.name_c like '%%%s%%'\n";
    public static final String JOIN_GENRE = "inner join movie_genre on (movie_genre.movie_id  = movie.id) inner join genre on (genre.id = movie_genre.genre_id)\n";
    public static final String WHERE = "where ";
    public static final String ORDER = "order by ";
    public static final String LIMIT = "offset %d limit %d";
    public static final String ORDER_RATE = "rate_r %s";
    public static final String ORDER_PRICE = "price_r %s";

    public String movieSearch(MovieSearchRequestDto movieSearchRequestDto){
        StringBuilder stringBuilderSelect = new StringBuilder();
        StringBuilder stringBuilderWhere = new StringBuilder();
        List<Object> variables = new ArrayList<>();

        stringBuilderSelect.append(SELECT_FROM_MOVIE);

        if (movieSearchRequestDto.getName() != null){
            stringBuilderWhere.append(stringBuilderWhere.length() == 0 ? "" : "or ").append(WHERE_NAME);
            variables.add(movieSearchRequestDto.getName());
        };

        if (movieSearchRequestDto.getOriginalName() != null){
            stringBuilderWhere.append(stringBuilderWhere.length() == 0 ? "" : "or ").append(WHERE_ORIGINAL_NAME);
            variables.add(movieSearchRequestDto.getOriginalName());
        };

        if (movieSearchRequestDto.getYear() != 0){
            stringBuilderWhere.append(stringBuilderWhere.length() == 0 ? "" : "or ").append(WHERE_YEAR);
            variables.add(movieSearchRequestDto.getYear());
        };

        if (movieSearchRequestDto.getCountry() != null){
            stringBuilderSelect.append(JOIN_COUNTRY);
            stringBuilderWhere.append(stringBuilderWhere.length() == 0 ? "" : "or ").append(WHERE_COUNTRY);
            variables.add(movieSearchRequestDto.getCountry().getName());
        };

        if (movieSearchRequestDto.getGenre() != null){
            stringBuilderSelect.append(JOIN_GENRE);
            stringBuilderWhere.append(stringBuilderWhere.length() == 0 ? "" : "or ").append(WHERE_GENRE);
            variables.add(movieSearchRequestDto.getGenre().getName());
        };

        if (stringBuilderWhere.length() != 0){
            stringBuilderWhere.insert(0, WHERE);
        }

        return String.format(stringBuilderSelect.append(stringBuilderWhere).toString(), variables.toArray());
    }

    public String getMovies(String ratingOrder, String priceOrder, int page){
        StringBuilder stringBuilderSelect = new StringBuilder();
        StringBuilder stringBuilderOrder = new StringBuilder();
        StringBuilder stringBuilderLimit = new StringBuilder();
        List<Object> variables = new ArrayList<>();

        stringBuilderSelect.append(SELECT_FROM_MOVIE);

        if (ratingOrder != "" && ratingOrder != null){
            stringBuilderOrder.append(stringBuilderOrder.length() == 0 ? "" : ", ").append(ORDER_RATE);
            variables.add(ratingOrder);
        }
        if (priceOrder != "" && priceOrder != null){
            stringBuilderOrder.append(stringBuilderOrder.length() == 0 ? "" : ", ").append(ORDER_PRICE);
            variables.add(priceOrder);
        }
        if (stringBuilderOrder.length() != 0){
            stringBuilderOrder.insert(0, ORDER).append("\n");
        }

        stringBuilderLimit.append(LIMIT);
        int offset = (page - 1) * Constant.MOVIES_PER_PAGE;
        variables.add(offset);
        variables.add(Constant.MOVIES_PER_PAGE);

        return String.format(stringBuilderSelect.append(stringBuilderOrder).append(stringBuilderLimit).toString(), variables.toArray());
    }
}

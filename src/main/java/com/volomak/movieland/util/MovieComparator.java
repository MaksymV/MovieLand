package com.volomak.movieland.util;

import com.volomak.movieland.entity.Movie;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {

    private String ratingOrder;
    private String priceOrder;

    public MovieComparator(String ratingOrder, String priceOrder) {
        this.ratingOrder = ratingOrder;
        this.priceOrder = priceOrder;
    }

    @Override
    public int compare(Movie movie1, Movie movie2) {

        int result = 0;

        if (ratingOrder.equals("asc")){
            result = movie1.getRate().compareTo(movie2.getRate());
        } else if (ratingOrder.equals("desc")) {
            result = - movie1.getRate().compareTo(movie2.getRate());
        }

        if (result == 0 && priceOrder.equals("asc")){
            result =  movie1.getPrice().compareTo(movie2.getPrice());
        } else if (result == 0 && priceOrder.equals("desc")) {
            result = - movie1.getPrice().compareTo(movie2.getPrice());
        }

        return result;
    }
}

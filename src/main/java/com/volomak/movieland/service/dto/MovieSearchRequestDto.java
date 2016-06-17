package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Country;
import com.volomak.movieland.entity.Genre;

import java.util.List;

/**
 * Created by grey4 on 17.06.2016.
 */
public class MovieSearchRequestDto {
    private Genre genre;
    private String name;
    private String originalName;
    private int year;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private Country country;

}

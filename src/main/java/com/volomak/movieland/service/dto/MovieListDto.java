package com.volomak.movieland.service.dto;

import java.util.List;

public class MovieListDto {
    private Long id;
    private String name;
    private String originalName;
    private int year;

    private String description;
    private Double rate;

    private List<GenreListDto> genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public List<GenreListDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreListDto> genres) {
        this.genres = genres;
    }
}


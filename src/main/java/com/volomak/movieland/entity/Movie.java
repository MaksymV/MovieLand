package com.volomak.movieland.entity;

import java.util.List;

public class Movie {
  private Long id;
  private String name;
  private String originalName;
  private int year;

  private String description;
  private Double rate;
  private Double price;

  List<Genre> genres;
  List<Country> countries;
  List<Review> reviews;

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

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public List<Country> getCountries() {
    return countries;
  }

  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Movie movie = (Movie) o;

    return id.equals(movie.id);

  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "Movie{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", originalName='" + originalName + '\'' +
            ", year=" + year +
            ", description='" + description + '\'' +
            ", rate=" + rate +
            ", price=" + price +
            ", genres=" + genres +
            ", countries=" + countries +
            ", reviews=" + reviews +
            '}';
  }
}

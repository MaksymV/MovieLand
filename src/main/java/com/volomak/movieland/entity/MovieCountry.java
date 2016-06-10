package com.volomak.movieland.entity;

public class MovieCountry {
  private Long movieId;
  private Long countryId;

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  @Override
  public String toString() {
    return "MovieCountry{" +
            "movieId=" + movieId +
            ", countryId=" + countryId +
            '}';
  }
}

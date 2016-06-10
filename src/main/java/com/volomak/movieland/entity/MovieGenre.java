package com.volomak.movieland.entity;

public class MovieGenre {
  private Long movieId;
  private Long genreId;

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getGenreId() {
    return genreId;
  }

  public void setGenreId(Long genreId) {
    this.genreId = genreId;
  }

  @Override
  public String toString() {
    return "MovieGenre{" +
            "movieId=" + movieId +
            ", genreId=" + genreId +
            '}';
  }
}

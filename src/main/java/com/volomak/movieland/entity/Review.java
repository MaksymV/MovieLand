package com.volomak.movieland.entity;

public class Review {
  private Long id;
  private Long movieId;
  private Long userId;
  private String review;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  @Override
  public String toString() {
    return "Review{" +
            "id=" + id +
            ", movieId=" + movieId +
            ", userId=" + userId +
            ", review='" + review + '\'' +
            '}';
  }
}

package com.volomak.movieland.service.dto;

public class ReviewRequestDto {
    private long id;
    private long moviewId;
    private long userId;
    private String review;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMoviewId() {
        return moviewId;
    }

    public void setMoviewId(long moviewId) {
        this.moviewId = moviewId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

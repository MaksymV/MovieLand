package com.volomak.movieland.service.dto;

public class ReviewRequestDto {
    private long moviewId;
    private long userId;
    private String Review;

    public long getMoviewId() {
        return moviewId;
    }

    public void setMoviewId(long moviewId) {
        this.moviewId = moviewId;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

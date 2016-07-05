package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.ReviewDao;
import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.ReviewService;
import com.volomak.movieland.service.dto.ReviewRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Override
    public List<Review> getByMovieId(Long id) {
        return reviewDao.getByMovieId(id);
    }

    @Override
    public Review addReview(ReviewRequestDto reviewRequestDto) {
        return reviewDao.addReview(reviewRequestDto);
    }

    @Override
    public int delReview(int reviewId) {
        return reviewDao.delReview(reviewId);
    }
}

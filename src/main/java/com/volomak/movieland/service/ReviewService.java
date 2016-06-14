package com.volomak.movieland.service;

import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.dto.ReviewListDto;

import java.util.List;

public interface ReviewService {
    Review getById(Long id);

    List<ReviewListDto> getByMovieId(Long id);
}

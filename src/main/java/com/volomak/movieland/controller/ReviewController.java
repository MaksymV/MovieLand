package com.volomak.movieland.controller;

import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.ReviewService;
import com.volomak.movieland.service.dto.ReviewRequestDto;
import com.volomak.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/v1")
public class ReviewController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/review", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Review addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        log.info("Start send request to add review");
        long startTime = System.currentTimeMillis();
        Review review = reviewService.addReview(reviewRequestDto);
        log.info("Finish request to add review. It took {} ms", System.currentTimeMillis() - startTime);
        return review;
    }

    @RequestMapping(value = "/review", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int delReview(@PathVariable int reviewId){
        log.info("Start send request to add review");
        long startTime = System.currentTimeMillis();
        int AffectedRows = reviewService.delReview(reviewId);
        log.info("Finish request to add review. It took {} ms", System.currentTimeMillis() - startTime);
        return AffectedRows;
    }


}

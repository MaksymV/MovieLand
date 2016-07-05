package com.volomak.movieland.controller;

import com.volomak.movieland.controller.error.RestrictAccessException;
import com.volomak.movieland.entity.Review;
import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.ReviewService;
import com.volomak.movieland.service.dto.ReviewRequestDto;
import com.volomak.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.volomak.movieland.util.PermittedRoles;

@Controller
@RequestMapping(value = "/v1")
public class ReviewController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private ReviewService reviewService;

    @PermittedRoles(roles = {"USER", "ADMIN"})
    @RequestMapping(value = "/review", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Review addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        log.info("Start send request to add review");
        long startTime = System.currentTimeMillis();
        Review review = reviewService.addReview(reviewRequestDto);
        log.info("Finish request to add review. It took {} ms", System.currentTimeMillis() - startTime);
        return review;
    }

    @PermittedRoles(roles = {"USER", "ADMIN"})
    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int delReview(@PathVariable ReviewRequestDto reviewRequestDto,
                         @RequestHeader(value = "authToken") String token) throws RestrictAccessException {
        log.info("Start send request to add review");
        long startTime = System.currentTimeMillis();
        int AffectedRows = reviewService.delReview(reviewRequestDto);
        log.info("Finish request to add review. It took {} ms", System.currentTimeMillis() - startTime);
        return AffectedRows;
    }


}

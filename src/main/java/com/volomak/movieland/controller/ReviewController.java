package com.volomak.movieland.controller;

import com.volomak.movieland.controller.error.RestrictAccessException;
import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.ReviewService;
import com.volomak.movieland.service.dto.ReviewRequestDto;
import com.volomak.movieland.util.PermittedRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/v1")
public class ReviewController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewService reviewService;

    @PermittedRoles(roles = {"USER", "ADMIN"})
    @RequestMapping(value = "/review", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Review> addReview(@RequestBody ReviewRequestDto reviewRequestDto, HttpServletRequest request){
        log.debug("Start send request to add review");
        long startTime = System.currentTimeMillis();
        Review review = reviewService.addReview(reviewRequestDto);
        log.debug("Finish request to add review. It took {} ms", System.currentTimeMillis() - startTime);
        return ResponseEntity.ok(review);
    }

    @PermittedRoles(roles = {"USER", "ADMIN"})
    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Integer> delReview(@PathVariable Long reviewId, HttpServletRequest request) throws RestrictAccessException {
        log.debug("Start send request to add review");
        long startTime = System.currentTimeMillis();
        String token  = request.getHeader("token");
        int AffectedRows = reviewService.delReview(reviewId, token);
        log.debug("Finish request to add review. It took {} ms", System.currentTimeMillis() - startTime);
        return ResponseEntity.ok(AffectedRows);
    }


}

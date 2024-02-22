package com.rowland.engineering.review.controller;

import com.rowland.engineering.review.model.Review;
import com.rowland.engineering.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api/v1/companies/{companyId}")
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<Review>> getAllCompanyReviews(@RequestParam String companyId){
//        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
//    }

    @PostMapping("/add-review")
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved)
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        return new ResponseEntity<>("Error! Not Saved!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);
        return new  ResponseEntity<>(review, HttpStatus.OK);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if (isReviewUpdated) {
            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error! Review not updated.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean deletedReview = reviewService.deleteReview(reviewId);
        if (deletedReview) {
            return new ResponseEntity<>("Deleted Successfully!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
    }
}

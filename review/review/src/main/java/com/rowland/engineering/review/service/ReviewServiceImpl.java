package com.rowland.engineering.review.service;

import com.rowland.engineering.review.model.Review;
import com.rowland.engineering.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null) {
//            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
        Review reviewFound = reviewRepository.findById(reviewId).orElse(null);
        if (reviewFound != null) {
            reviewFound.setCompanyId(review.getCompanyId());
            reviewFound.setTitle(reviewFound.getTitle());
            reviewFound.setDescription(review.getDescription());
            reviewFound.setRating(review.getRating());
            reviewRepository.save(reviewFound);
            return true;
        }
        return false;


    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}

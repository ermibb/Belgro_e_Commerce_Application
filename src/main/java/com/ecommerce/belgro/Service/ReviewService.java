package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.Review;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest req, User user, Product product); // no usages

    List<Review> getReviewByProductId(Long productId); // no usages

    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;

    void deleteReview(Long reviewId, Long userId) throws Exception; // no usages
    Review getReviewById(Long reviewId) throws Exception;
}

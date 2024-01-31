package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.ProductException;
import com.ecommerce.backend.model.Review;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user)throws ProductException;
    public List<Review> getAllReview(Long productId);
}

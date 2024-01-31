package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.ProductException;
import com.ecommerce.backend.model.Rating;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);

}

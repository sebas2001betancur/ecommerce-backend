package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.exception.ProductException;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.Rating;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.RatingRepository;
import com.ecommerce.backend.request.RatingRequest;
import com.ecommerce.backend.service.ProductService;
import com.ecommerce.backend.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}

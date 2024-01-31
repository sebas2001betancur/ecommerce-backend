package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.UserException;
import com.ecommerce.backend.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}

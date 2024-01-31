package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.CartItemException;
import com.ecommerce.backend.exception.UserException;
import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.model.CartItem;
import com.ecommerce.backend.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws  CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}

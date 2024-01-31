package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.model.OrderItem;
import com.ecommerce.backend.repository.OrderItemRepository;
import com.ecommerce.backend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}

package com.OrderManagement.OrderManagement.service;

import com.OrderManagement.OrderManagement.model.OrderModel;
import com.OrderManagement.OrderManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderModel getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public OrderModel createOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public OrderModel updateOrder(Long orderId, OrderModel updatedOrder) {
        Optional<OrderModel> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            OrderModel order = existingOrder.get();
            order.setUserId(updatedOrder.getUserId());
            order.setOrderDate(updatedOrder.getOrderDate());
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }
}

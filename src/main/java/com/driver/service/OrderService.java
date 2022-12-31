package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto orderID) {
        OrderEntity orderEntity=OrderEntity.builder().orderId(orderID.getOrderId()).id(orderID.getId()).cost(orderID.getCost()).items(orderID.getItems()).userId(orderID.getUserId()).status(orderID.isStatus()).build();
        orderRepository.save(orderEntity);
        return OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }

    @Override //Overriding
    public OrderDto getOrderById(String orderID) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderID);
        return OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setCost(order.getCost());
        orderEntity.setId(order.getId());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(order.isStatus());
        orderEntity.setUserId(order.getUserId());
        orderEntity=orderRepository.save(orderEntity);
        return OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }

    @Override
    public void deleteOrder(String order) throws Exception {
        orderRepository.delete(orderRepository.findByOrderId(order));
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDto=null;
        List<OrderEntity> orderEntities= (List<OrderEntity>) orderRepository.findAll();
        for(OrderEntity orderEntity:orderEntities){
            orderDto.add(OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build());
        }
        return orderDto;
    }
}

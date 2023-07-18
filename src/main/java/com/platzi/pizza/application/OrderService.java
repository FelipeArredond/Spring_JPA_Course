package com.platzi.pizza.application;

import com.platzi.pizza.persistence.entities.OrderEntity;
import com.platzi.pizza.persistence.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        List<OrderEntity> all = this.orderRepository.findAll();
        all.forEach(orderEntity ->  log.info(orderEntity.getCustomer().getName()));
        return all;
    }

    public Optional<OrderEntity> get(long id){
        return this.orderRepository.findById(id);
    }
}

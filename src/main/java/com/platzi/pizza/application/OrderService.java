package com.platzi.pizza.application;

import com.platzi.pizza.persistence.entities.OrderEntity;
import com.platzi.pizza.persistence.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

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

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime dateTime = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(dateTime);
    }

    public List<OrderEntity> getOutsideOrders(){
        return this.orderRepository.findAllByMethodIn(Arrays.asList(DELIVERY, CARRYOUT));
    }

}

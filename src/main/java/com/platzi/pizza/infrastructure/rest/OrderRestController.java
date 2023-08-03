package com.platzi.pizza.infrastructure.rest;

import com.platzi.pizza.application.OrderService;
import com.platzi.pizza.persistence.entities.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }
    @GetMapping("/by_customer/{idCostumer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCostumer){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCostumer));
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getCustomerOrders(@PathVariable Long id){
        return ResponseEntity.ok(this.orderService.getSummary(id));
    }
    @GetMapping("/today_orders")
    public ResponseEntity<List<OrderEntity>> getAllToday(){
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside_orders")
    public ResponseEntity<List<OrderEntity>> getOutside(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }
}

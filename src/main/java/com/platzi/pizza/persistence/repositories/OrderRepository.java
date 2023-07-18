package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
}

package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime dateTime);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

}

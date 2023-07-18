package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.PizzaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    List<PizzaEntity> findAllByName(String name);

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    List<PizzaEntity> findAllByAvailableTrueAndNameContainingIgnoreCase(String name);
}

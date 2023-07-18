package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.PizzaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
}

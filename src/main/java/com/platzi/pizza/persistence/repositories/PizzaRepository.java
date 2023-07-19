package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.dto.UpdatePizzaDto;
import com.platzi.pizza.persistence.entities.PizzaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    List<PizzaEntity> findAllByName(String name);
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    List<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String name);
    Integer countByVeganTrue();

    @Query(value = "UPDATE pizza " +
                   "SET price = :#{#editPizzaDto.newPrice} " +
                   "WHERE id_pizza = :#{#editPizzaDto.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("editPizzaDto")UpdatePizzaDto editPizzaDto);
}

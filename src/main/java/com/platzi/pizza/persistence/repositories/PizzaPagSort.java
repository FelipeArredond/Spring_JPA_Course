package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaPagSort extends ListPagingAndSortingRepository<PizzaEntity, Long> {
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}

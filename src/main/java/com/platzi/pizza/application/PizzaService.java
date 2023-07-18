package com.platzi.pizza.application;

import com.platzi.pizza.persistence.entities.PizzaEntity;
import com.platzi.pizza.persistence.repositories.PizzaPagSort;
import com.platzi.pizza.persistence.repositories.PizzaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSort pizzaPagSort;

    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSort pizzaPagSort) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSort = pizzaPagSort;
    }

    public Page<PizzaEntity> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        return this.pizzaPagSort.findAll(pageRequest);
    }

    public Optional<PizzaEntity> get(Long id){ return this.pizzaRepository.findById(id); }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    public void delete(long id){
        this.pizzaRepository.deleteById(id);
    }

    public boolean exist(String name){
        return this.pizzaRepository.findAllByName(name).isEmpty();
    }

    public boolean existById(long id){
        return this.pizzaRepository.existsById(id);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSort.findByAvailableTrue(pageRequest);
    }

    public List<PizzaEntity> getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getByDescriptionContaining(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(name);
    }
    public List<PizzaEntity> getByDescriptionNotContaining(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(name);
    }

}

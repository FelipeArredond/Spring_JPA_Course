package com.platzi.pizza.application;

import com.platzi.pizza.persistence.entities.PizzaEntity;
import com.platzi.pizza.persistence.repositories.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
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

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
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

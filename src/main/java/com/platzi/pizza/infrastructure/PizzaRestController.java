package com.platzi.pizza.infrastructure;

import com.platzi.pizza.application.PizzaService;
import com.platzi.pizza.persistence.entities.PizzaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaRestController {
    private final PizzaService pizzaService;

    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("")
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return new ResponseEntity<>(this.pizzaService.getAll(), HttpStatus.OK);
    }
}

package com.platzi.pizza.infrastructure;

import com.platzi.pizza.application.PizzaService;
import com.platzi.pizza.persistence.entities.PizzaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza")
@Slf4j
public class PizzaRestController {
    private final PizzaService pizzaService;

    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("")
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return new ResponseEntity<>(this.pizzaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAllAvailable(){
        return new ResponseEntity<>(this.pizzaService.getAvailable(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PizzaEntity>> getAllAvailable(@PathVariable String name){
        return new ResponseEntity<>(this.pizzaService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/containing/{item}")
    public ResponseEntity<List<PizzaEntity>> getAllContains(@PathVariable String item){
        return new ResponseEntity<>(this.pizzaService.getByDescriptionContaining(item), HttpStatus.OK);
    }

    @GetMapping("/notcontaining/{item}")
    public ResponseEntity<List<PizzaEntity>> getAllNotContains(@PathVariable String item){
        return new ResponseEntity<>(this.pizzaService.getByDescriptionNotContaining(item), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable Long id){
        return new ResponseEntity(this.pizzaService.get(id), HttpStatus.FOUND);
    }
    @PostMapping("")
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity){
        if(!this.pizzaService.exist(pizzaEntity.getName())){
            log.error("Pizza already exist");
            return ResponseEntity.badRequest().build();
        }
        log.info("Posting");
        return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
    }

    @PutMapping("")
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity){
        if(!this.pizzaService.exist(pizzaEntity.getName())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id){
        if(this.pizzaService.existById(id)){
            this.pizzaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

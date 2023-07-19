package com.platzi.pizza.infrastructure;

import com.platzi.pizza.application.PizzaService;
import com.platzi.pizza.persistence.dto.UpdatePizzaDto;
import com.platzi.pizza.persistence.entities.PizzaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int size){
        return new ResponseEntity<>(this.pizzaService.getAll(page, size), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int size,
                                                             @RequestParam(defaultValue = "name") String sortBy,
                                                             @RequestParam(defaultValue = "DESC") String sortDirection){
        return new ResponseEntity<>(this.pizzaService.getAvailable(page, size, sortBy, sortDirection), HttpStatus.OK);
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

    @PutMapping("/price")
    public ResponseEntity<Void> updatePizza(@RequestBody UpdatePizzaDto updatePizzaDto){
        if(this.pizzaService.existById(updatePizzaDto.getPizzaId())){
            this.pizzaService.updatePizza(updatePizzaDto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
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

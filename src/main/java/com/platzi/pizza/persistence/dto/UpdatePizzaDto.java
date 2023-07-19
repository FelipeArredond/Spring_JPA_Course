package com.platzi.pizza.persistence.dto;

import lombok.Data;

@Data
public class UpdatePizzaDto {
    private int pizzaId;
    private double newPrice;
}

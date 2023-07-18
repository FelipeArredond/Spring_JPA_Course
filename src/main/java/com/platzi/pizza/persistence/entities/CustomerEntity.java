package com.platzi.pizza.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @Column(nullable = false, unique = true, name = "id_customer")
    private String idCustomer;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20, name = "phone_number")
    private String phoneNumber;
}

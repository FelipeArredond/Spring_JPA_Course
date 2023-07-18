package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhone(@PathVariable("phone") String phone);
}

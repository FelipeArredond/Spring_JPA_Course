package com.platzi.pizza.application;

import com.platzi.pizza.persistence.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock CustomerRepository customerRepository;
    CustomerService customerService;

    @BeforeEach
    void setUp(){
        this.customerService = new CustomerService(customerRepository);
    }

    @Test
    void canFindByPhone() {
        // given
        // when
        this.customerService.findByPhone("");
        // then
        verify(this.customerRepository).findByPhone("");
    }
}
package com.example.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.customer.model.Customer;

@Repository
public interface customerRepositary extends CrudRepository<Customer, Integer> {
    
}

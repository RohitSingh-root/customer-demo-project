package com.example.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.Customer;
import com.example.customer.service.crudService;

@RestController
public class crudController {

    @Autowired
    crudService service;
    
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<String>(service.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<Customer> getCustomer(@RequestParam int cust_id){
        return new ResponseEntity<Customer>(service.getCustomer(cust_id), HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return new ResponseEntity<Customer>(service.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestParam int cust_id){
        service.deleteCustomer(cust_id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}

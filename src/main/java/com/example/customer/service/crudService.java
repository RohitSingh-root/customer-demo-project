package com.example.customer.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.exception.InvalidDataException;
import com.example.customer.model.Customer;
import com.example.customer.repository.customerRepositary;

@Service
public class crudService {

    customerRepositary repository;

    @Autowired
    public crudService(customerRepositary repository){
        this.repository= repository;
    }



    private String email_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private String ph_number_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    private String gender_REGEX= "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE|Not prefer to say)$";

    public String createCustomer(Customer customer){
        this.validate(customer);
        repository.save(customer);
        return "Customer registered successfully";
    }

    public Customer getCustomer(int cust_id) {
        return repository.findById(cust_id).orElseThrow(()->new RuntimeException("Customer Not Found"));
    }

    public Customer updateCustomer(Customer customer) {
        this.validate(customer);
        Customer existingCustomer= repository.findById(customer.getCust_id()).orElseThrow(()->new RuntimeException(" Customer Not Found"));
        existingCustomer.setCust_name(customer.getCust_name());
        existingCustomer.setCust_email(customer.getCust_email());
        existingCustomer.setCust_ph_number(customer.getCust_ph_number());
        existingCustomer.setCust_gender(customer.getCust_gender());
        repository.save(existingCustomer);
        return existingCustomer;
    }

    public String deleteCustomer(int cust_id) {
        repository.deleteById(cust_id);
        return "Customer deleted successfully";
    }

    public void validate(Customer customer){
        if(!(Integer.toString(customer.getCust_id()).length()==5)){
            throw new InvalidDataException("Please enter a valid Id. Id should be 5 digit");
        }
        if(customer.getCust_name().length()<1){
            throw new InvalidDataException("Please enter a valid name");
        }
        if(!Pattern.compile(email_REGEX).matcher(customer.getCust_email()).matches()){
            throw new InvalidDataException("Please enter a valid email");
        }
        if(!Pattern.compile(ph_number_REGEX).matcher(Integer.toString(customer.getCust_ph_number())).matches()){
            throw new InvalidDataException("Please enter a valid Phone Number");
        }
        if(!Pattern.compile(gender_REGEX).matcher(customer.getCust_gender()).matches()){
            throw new InvalidDataException("Please enter a valid gender");
        }
    }
}

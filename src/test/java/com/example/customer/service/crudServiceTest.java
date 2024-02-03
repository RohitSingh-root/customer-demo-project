package com.example.customer.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.customer.exception.InvalidDataException;
import com.example.customer.model.Customer;
import com.example.customer.repository.customerRepositary;

public class crudServiceTest {
    
    @MockBean
    private customerRepositary repository;

    private crudService service;

    @BeforeEach
    public void setup(){
        repository= mock(customerRepositary.class);
        service = new crudService(repository);
    }

    @Test
    public void getCustomerTest(){
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"M");
        when(this.repository.findById(12345)).thenReturn(Optional.of(customer));

        Customer actualCustomer = this.service.getCustomer(12345);

        assertThat(customer.getCust_name(),equalTo(actualCustomer.getCust_name()));
        assertThat(customer.getCust_email(),equalTo(actualCustomer.getCust_email()));
        assertThat(customer.getCust_gender(),equalTo(actualCustomer.getCust_gender()));
        assertThat(customer.getCust_id(),equalTo(actualCustomer.getCust_id()));
        assertThat(customer.getCust_ph_number(),equalTo(actualCustomer.getCust_ph_number()));
    }

    @Test
    public void getCustomerNotFoundExceptionTest(){
        when(this.repository.findById(12345)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(()-> service.getCustomer(12345)).isInstanceOf(RuntimeException.class).descriptionText().equals("Customer Not Found");
    }
    
    @Test
    public void deleteCustomerTest(){
        String result= this.service.deleteCustomer(12345);
        assertThat("Customer deleted successfully",equalTo(result));
    }

    @Test
    public void createCustomerTest(){
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"M");
        String result= this.service.createCustomer(customer);
        assertThat("Customer registered successfully",equalTo(result));
    }

    @Test
    public void createCustomerWithInvalidEmailTest(){
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohitgmail.com",1234567890,"M");
        assertThatThrownBy(()-> service.createCustomer(customer)).isInstanceOf(InvalidDataException.class).descriptionText().equals("Please enter a valid email");
    }

    @Test
    public void createCustomerWithInvalidNameTest(){
        Customer customer= new Customer();
        customer.customer(12345,"","rohit@gmail.com",1234567890,"M");
        assertThatThrownBy(()-> service.createCustomer(customer)).isInstanceOf(InvalidDataException.class).descriptionText().equals("Please enter a valid name");
    }

    @Test
    public void createCustomerWithInvalidIdTest(){
        Customer customer= new Customer();
        customer.customer(123,"Rohit","rohit@gmail.com",1234567890,"M");
        assertThatThrownBy(()-> service.createCustomer(customer)).isInstanceOf(InvalidDataException.class).descriptionText().equals("Please enter a valid Id. Id should be 5 digit");
    }

    @Test
    public void createCustomerWithInvalidPhNumberTest(){
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234590,"M");
        assertThatThrownBy(()-> service.createCustomer(customer)).isInstanceOf(InvalidDataException.class).descriptionText().equals("Please enter a valid Phone Number");
    }

    @Test
    public void createCustomerWithInvalidGenderTest(){
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"AA");
        assertThatThrownBy(()-> service.createCustomer(customer)).isInstanceOf(InvalidDataException.class).descriptionText().equals("Please enter a valid gender");
    }

    @Test
    public void updateCustomerTest(){
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"M");
        when(this.repository.findById(12345)).thenReturn(Optional.of(customer));

        Customer actualCustomer= this.service.updateCustomer(customer);

        assertThat(customer.getCust_name(),equalTo(actualCustomer.getCust_name()));
        assertThat(customer.getCust_email(),equalTo(actualCustomer.getCust_email()));
        assertThat(customer.getCust_gender(),equalTo(actualCustomer.getCust_gender()));
        assertThat(customer.getCust_id(),equalTo(actualCustomer.getCust_id()));
        assertThat(customer.getCust_ph_number(),equalTo(actualCustomer.getCust_ph_number()));

    }

    @Test
    public void updateCustomerNotFoundExceptionTest(){
        Customer customer= new Customer();
        customer.customer(12344,"Rohit","rohit@gmail.com",1234567890,"M");
        when(this.repository.findById(12345)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(()-> service.updateCustomer(customer)).isInstanceOf(RuntimeException.class).descriptionText().equals(" Customer Not Found");
    }
}

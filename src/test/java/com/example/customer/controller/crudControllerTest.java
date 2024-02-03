package com.example.customer.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.customer.model.Customer;
import com.example.customer.service.crudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


@WebMvcTest(crudController.class)
public class crudControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private crudService service;

    @Test
    public void getCustomerTest() throws Exception{
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"M");
        when(this.service.getCustomer(anyInt())).thenReturn(customer);
        mockMvc.perform(get("/get").param("cust_id", "12345")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception{
        when(this.service.deleteCustomer(12345)).thenReturn("Customer deleted successfully");
        mockMvc.perform(delete("/delete").param("cust_id", "12345")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void createCustomerTest() throws Exception{
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"M");
        when(this.service.createCustomer(customer)).thenReturn("Customer registered successfully");

        ObjectMapper mapper= new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(customer);

        mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isCreated())
        .andExpect(content().string("Customer registered successfully"));
    }

    @Test
    public void updateCustomerTest() throws Exception{
        Customer customer= new Customer();
        customer.customer(12345,"Rohit","rohit@gmail.com",1234567890,"M");
        when(this.service.updateCustomer(customer)).thenReturn(customer);

        ObjectMapper mapper= new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(customer);

        mockMvc.perform(put("/put").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print())
        .andExpect(jsonPath("cust_name").value("Rohit"))
        .andExpect(jsonPath("cust_email").value("rohit@gmail.com"));
    }
}
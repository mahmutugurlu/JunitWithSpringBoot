package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @Test
    void getCustomer() {
        //verilen
        Customer customer=new Customer(1L,"Ali","Can","alican");
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        //işlem
        ResponseEntity<Customer> response =customerController.getCustomer(1L);

        //beklenen
        assertEquals(customer,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        verify(customerService,times(1)).getCustomerById(1L);

    }

    @Test
    void deleteCustomer() {

        //işlem
        ResponseEntity<String> response=customerController.deleteCustomer(1L);

        //beklenen
        verify(customerService,times(1)).deleteCustomer(1L);
        assertEquals("Customer is deleted.",response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());


    }












}
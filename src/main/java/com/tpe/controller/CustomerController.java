package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    //idsi verilen customerı görüntüleme
    //request:localhost:8080/customers/1 + GET

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {

        Customer customer=customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);//200

    }


    //idsi verilen customerı silme
    //request:localhost:8080/customers/1 + DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){

        customerService.deleteCustomer(id);

        return new ResponseEntity<>("Customer is deleted.",HttpStatus.OK);

    }




}

package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {

        Customer customer=customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer is not found!"));

        return customer;

    }

    public void deleteCustomer(Long id) {

        getCustomerById(id);
        customerRepository.deleteById(id);

    }





}

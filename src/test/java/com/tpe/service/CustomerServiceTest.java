package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // @ExtendWith anotasyonu mock,injectmocks gibi anotasyonlarin kullanilmasini saglar
class CustomerServiceTest {

    //bu classta
    //1-CustomerService objesi gerekli
    //2-CustomerService objesi CustomerRepository objesine bağımlı
    //fakat CustomerRepository objesi yerine mock kullanmalıyız

    @Mock//gerçek obje değil mock obje oluştur
    private CustomerRepository customerRepository;

    @InjectMocks//içine gerçek bağımlılığını değil mock anotasyonu ile belirttiğimiz
    //vekil objelerin enjekte edilmesini sağlar
    private CustomerService customerService;



    @Test
    void getCustomerById() {
        //verilen

        Customer customer=new Customer(1L,"Ali","Can","alican");

        //mock obje nasıl davranacak
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        when(customerRepository.findById(99L)).thenReturn(Optional.empty()); //olumsuz senaryo

        //aksiyon-anlik
        Customer actualCustomer=customerService.getCustomerById(1L);

        //assert-beklenen
        assertEquals(customer,actualCustomer);//pozitif senaryo

        assertThrows(RuntimeException.class,()->customerService.getCustomerById(99L)); //negatif senaryo

        //reponun metodu aynı id ile 1 kere çağrılmış mı
        verify(customerRepository,times(1)).findById(1L);

        verify(customerRepository,times(1)).findById(99L);




    }

    @Test
    void deleteCustomer() {
        //verilenler
        Customer customer=new Customer(1L,"Ali","Can","alican");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        //negatifsenaryo
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        //işlem
        customerService.deleteCustomer(1L);
        verify(customerRepository,times(1)).deleteById(1L);

        assertThrows(RuntimeException.class, ()->customerService.deleteCustomer(99L));

    }



}
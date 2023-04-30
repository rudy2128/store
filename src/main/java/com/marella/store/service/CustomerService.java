package com.marella.store.service;

import com.marella.store.model.Category;
import com.marella.store.model.Customer;
import com.marella.store.model.Supplier;
import com.marella.store.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer>findAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.getReferenceById(id);
    }


    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

    public List<Customer>findByKeyword(String keyword){
        return customerRepository.findByKeyword(keyword);
    }
}

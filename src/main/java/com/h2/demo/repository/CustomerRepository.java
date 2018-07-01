package com.h2.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.h2.demo.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
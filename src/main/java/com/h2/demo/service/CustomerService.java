package com.h2.demo.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.h2.demo.model.Customer;
import com.h2.demo.repository.CustomerRepository;
import com.h2.demo.utilities.RandomObjectFiller;

@Repository
public class CustomerService {

	@PostConstruct
	public void insert() throws Exception {
		Customer customer = RandomObjectFiller.createObject(Customer.class);
		customerRepository.save(customer);

	}

	@Autowired
	private CustomerRepository customerRepository;

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Optional<Customer> findById(long id) {
		return customerRepository.findById(id);
	}

	public void deleteById(long id) {
		customerRepository.deleteById(id);
	}

	public long insert(Customer customer) {
		customer = customerRepository.save(customer);
		return customer.getCustomerNo();
	}

	public void update(Customer customer) {
		customerRepository.save(customer);
	}

}

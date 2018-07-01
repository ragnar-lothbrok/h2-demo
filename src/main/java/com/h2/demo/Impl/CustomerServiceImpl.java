package com.h2.demo.Impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.h2.demo.model.Customer;
import com.h2.demo.repository.CustomerRepository;
import com.h2.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Async
	public CompletableFuture<Optional<Customer>> findById(long id) {
		return customerRepository.findOneById(id);
	}

	@Override
	@Async
	public CompletableFuture<List<Customer>> findAllCustomers() {
		return customerRepository.findAllCustomers();
	}

}

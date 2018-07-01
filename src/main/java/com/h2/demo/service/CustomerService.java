package com.h2.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.h2.demo.model.Customer;

public interface CustomerService {

	public CompletableFuture<List<Customer>> findAllCustomers();

	public CompletableFuture<Optional<Customer>> findById(long id);

}

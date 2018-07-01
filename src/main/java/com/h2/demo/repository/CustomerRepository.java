package com.h2.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.h2.demo.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Async
	CompletableFuture<List<Customer>> findAllCustomers();

	@Async
	CompletableFuture<Optional<Customer>> findOneById(final Long id);
}
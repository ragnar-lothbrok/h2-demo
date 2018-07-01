package com.h2.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h2.demo.model.Customer;
import com.h2.demo.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Async
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CompletableFuture<ResponseEntity<List<Customer>>> getCustomers() {
		return customerService.findAllCustomers().<ResponseEntity<List<Customer>>>thenApply(ResponseEntity::ok)
				.exceptionally(handleGetUsersFailure);
	}

	@Async
	@GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CompletableFuture<ResponseEntity<Customer>> getUser(@PathVariable final Long customerId) {
		return customerService.findById(customerId).thenApply(mapMaybeUserToResponse)
				.exceptionally(handleGetUserFailure.apply(customerId));
	}

	private static Function<Throwable, ResponseEntity<List<Customer>>> handleGetUsersFailure = throwable -> {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	};

	private static Function<Optional<Customer>, ResponseEntity<Customer>> mapMaybeUserToResponse = maybeUser -> maybeUser
			.<ResponseEntity<Customer>>map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	private static Function<Long, Function<Throwable, ResponseEntity<Customer>>> handleGetUserFailure = userId -> throwable -> {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	};
}

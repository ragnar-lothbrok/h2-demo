package com.h2.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.h2.demo.config.DataBaseConfig;
import com.h2.demo.model.Customer;
import com.h2.demo.utilities.RandomObjectFiller;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DataBaseConfig.class })
@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	private Long customerId;

	@Before
	public void setup() throws Exception {
		Customer customer = RandomObjectFiller.createObject(Customer.class);
		customer = customerRepository.save(customer);
		customerId = customer.getCustomerNo();
	}

	@Test
	public void findById() {
		Optional<Customer> customer = customerRepository.findById(customerId);
		assertEquals(true, customer.get() != null);
	}

	@Test
	public void insert() throws Exception {
		Customer customer = RandomObjectFiller.createObject(Customer.class);
		customer = customerRepository.save(customer);

		Optional<Customer> savedCustomer = customerRepository.findById(customer.getCustomerNo());
		assertThat(savedCustomer.isPresent()).isEqualTo(true);
	}

	@Test
	public void delete() throws Exception {
		Customer customer = RandomObjectFiller.createObject(Customer.class);
		customer = customerRepository.save(customer);

		Optional<Customer> savedCustomer = customerRepository.findById(customer.getCustomerNo());
		assertThat(savedCustomer.isPresent()).isEqualTo(true);

		customerRepository.deleteById(savedCustomer.get().getCustomerNo());

		savedCustomer = customerRepository.findById(customer.getCustomerNo());
		assertThat(savedCustomer.isPresent()).isEqualTo(false);
	}
}

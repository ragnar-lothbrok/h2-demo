package com.h2.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		customerId = customer.getCustomerNo();
		customerRepository.insert(customer);
	}

	@Test
	public void findAll() {
		List<Customer> customers = customerRepository.findAll();
		assertEquals(true, customers.size() > 0);
	}

	public void findById() {
		Customer customer = customerRepository.findById(customerId);
		assertEquals(true, customer != null);
	}

	public void deleteById() {
		int result = customerRepository.deleteById(customerId);
		assertEquals(1, result);
	}

	public void insert() throws Exception {
		Customer customer = RandomObjectFiller.createObject(Customer.class);
		customerRepository.insert(customer);

		Customer savedCustomer = customerRepository.findById(customerId);
		assertEquals(customer.getCustomerNo(), savedCustomer.getCustomerNo());
	}

	public void update() throws Exception {
		Customer customer = RandomObjectFiller.createObject(Customer.class);
		customerRepository.insert(customer);

		String firstName = customer.getFirstName();

		customer.setFirstName("D");

		customerRepository.update(customer);

		customer = customerRepository.findById(customerId);

		assertEquals(firstName, customer.getFirstName());
	}

}

package com.h2.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.h2.demo.mappers.CustomerMapper;
import com.h2.demo.model.Customer;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public List<Customer> findAll() {
		return jdbcTemplate.query("select * from customer", new CustomerMapper());
	}

	public Customer findById(long id) {
		return jdbcTemplate.queryForObject("select * from customer where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	public int deleteById(long id) {
		return jdbcTemplate.update("delete from customer where id=?", new Object[] { id });
	}

	public int insert(Customer customer) {
		return jdbcTemplate.update(
				"insert into customer (id, first_name, last_name, birth_date,gender) " + "values(?, ?,?, ?, ?)",
				new Object[] { customer.getCustomerNo(), customer.getFirstName(), customer.getLastName(),
						customer.getBirthDate(), customer.getGender() });
	}

	public int update(Customer customer) {
		return jdbcTemplate.update("update customer  set first_name = ?, last_name = ? " + " where id = ?",
				new Object[] { customer.getCustomerNo(), customer.getFirstName(), customer.getLastName() });
	}

}

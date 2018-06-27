package com.h2.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {

	private static final long serialVersionUID = 2498473733291946169L;

	private Long customerNo;
	private Date birthDate;
	private String firstName;
	private String lastName;
	private String gender;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(Long customerNo) {
		this.customerNo = customerNo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}

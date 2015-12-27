package com.gbz.yaps.petstore.ejb.stateless.customer;

import javax.ejb.Local;

import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.entity.customer.Customer;

@Local
public interface CustomerServiceLocal {

	Customer authenticate(String login, String password);
	Customer createCustomer(Customer customer, Address address);
	Customer findCustomer(Long customerId);
	Customer updateCustomer(Customer customer,Address address);

}

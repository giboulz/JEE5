package com.gbz.yaps.petstore.ejb.stateless.customer;

import java.util.List;

import javax.ejb.Remote;

import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.entity.customer.Customer;

@Remote
public interface CustomerServiceRemote {
	
	Customer createCustomer(Customer customer, Address address) ;
	Customer findCustomer(Long customerId) ;
	void deleteCustomer(Customer customer) ;
	Customer updateCustomer(Customer customer, Address address) ;
	List<Customer> findCustomers() ;


}

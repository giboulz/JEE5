package com.gbz.yaps.petstore.ejb.stateless.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.gbz.yaps.petstore.ejb.stateless.customer.CustomerServiceLocal;
import com.gbz.yaps.petstore.ejb.stateless.customer.CustomerServiceRemote;
import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.entity.customer.Customer;
import com.gbz.yaps.petstore.exception.ValidationException;

@TransactionAttribute(value = TransactionAttributeType.REQUIRED)

@Stateless(name = "CustomerSB", mappedName = "ejb/stateless/Customer")
// @Remote(CustomerServiceRemote.class)
// @Local
public class CustomerServiceImpl implements CustomerServiceLocal, CustomerServiceRemote {

	@PersistenceContext(unitName = "petStorePU")
	private EntityManager em;

	public Customer authenticate(String login, String password) {
		if (login == null || "".equals(login))
			throw new ValidationException("Invalid login");
		Query query;
		Customer customer;
		query = em.createQuery("SELECT c FROM Customer c WHERE c.login=:login");
		query.setParameter("login", login);
		customer = (Customer) query.getSingleResult();
		if (customer != null)
			customer.matchPassword(password);
		return customer;
	}

	public Customer createCustomer(Customer customer, Address homeAddress) {
		if (customer == null)
			throw new ValidationException("Customer object is null");
		customer.setHomeAddress(homeAddress);
		em.persist(customer);
		return customer;
	}

	public Customer findCustomer(Long customerId) {
		if (customerId == null)
			throw new ValidationException("Invalid id");
		Customer customer;
		customer = em.find(Customer.class, customerId);
		return customer;
	}

	public void deleteCustomer(Customer customer) {
		if (customer == null)
			throw new ValidationException("Customer object is null");
		em.remove(em.merge(customer));
	}

	public Customer updateCustomer(Customer customer, Address homeAddress) {
		if (customer == null)
			throw new ValidationException("Customer object is null");
		customer.setHomeAddress(homeAddress);
		em.merge(customer);
		return customer;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomers() {
		Query query;
		List<Customer> customers;
		query = em.createQuery("SELECT c FROM Customer c");
		customers = (List<Customer>) query.getResultList();
		return customers;
	}
}

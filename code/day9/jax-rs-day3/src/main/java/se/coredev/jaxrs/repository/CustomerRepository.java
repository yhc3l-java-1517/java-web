package se.coredev.jaxrs.repository;

import se.coredev.jaxrs.model.Customer;

public interface CustomerRepository {

	void add(Customer customer);

	Customer get(Long id);

	void update(Customer customer);

	boolean delete(Long id);

}

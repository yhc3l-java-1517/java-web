package se.coredev.jaxrs.repository;

import java.util.HashMap;
import java.util.Map;

import se.coredev.jaxrs.model.Customer;

public final class InMemoryCustomerRepository implements CustomerRepository {

	private final Map<Long, Customer> customers = new HashMap<>();

	@Override
	public void add(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	@Override
	public Customer get(Long id) {
		return customers.get(id);
	}

	@Override
	public void update(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	@Override
	public boolean delete(Long id) {
		return customers.remove(id) != null;
	}
}

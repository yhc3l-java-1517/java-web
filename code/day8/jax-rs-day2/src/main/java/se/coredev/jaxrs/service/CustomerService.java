package se.coredev.jaxrs.service;

import java.util.concurrent.atomic.AtomicLong;

import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.repository.CustomerRepository;

public final class CustomerService {

	private final CustomerRepository customerRepository;
	private final AtomicLong customerIds = new AtomicLong(1000);

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer createCustomer(Customer customer) {

		Long id = customerIds.incrementAndGet();
		Customer c = new Customer(id, customer.getFirstName(), customer.getLastName(), customer.getCustomerNumber());
		customerRepository.add(c);

		return c;
	}

	public Customer getCustomer(Long id) {
		return customerRepository.get(id);
	}

	public Customer updateCustomer(Customer customer) {
		customerRepository.update(customer);
		return customer;
	}

	public boolean deleteCustomer(Long id) {
		return customerRepository.delete(id);
	}

}

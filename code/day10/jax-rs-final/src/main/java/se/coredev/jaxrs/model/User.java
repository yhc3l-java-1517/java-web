package se.coredev.jaxrs.model;

import java.util.Collection;

public final class User {

	private final Long id;
	private final String firstName;
	private final String lastName;
	private final Collection<String> roles;

	public User(Long id, String firstName, String lastName, Collection<String> roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public User addRole(String role) {
		roles.add(role);
		return this;
	}

	public Collection<String> getRoles() {
		return roles;
	}
}

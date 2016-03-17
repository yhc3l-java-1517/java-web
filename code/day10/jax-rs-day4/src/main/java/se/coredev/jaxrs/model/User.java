package se.coredev.jaxrs.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class User {

	@XmlElement
	private final Long id;
	@XmlElement
	private final String firstName;
	@XmlElement
	private final String lastName;
	@XmlElement(name = "role")
	@XmlElementWrapper(name = "roles")
	private final Collection<String> roles;

	@SuppressWarnings("unused")
	private User() {
		this(-1L, "", "");
	}

	public User(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = new ArrayList<String>();
		roles.add("regular");
		roles.add("guest");
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

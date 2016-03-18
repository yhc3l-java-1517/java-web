package se.coredev.jaxrs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class User {

	@XmlElement
	private final Long id;
	@XmlElement
	private final String firstName;
	@XmlElement
	private final String lastName;

	@SuppressWarnings("unused")
	private User() {
		this(-1L, "", "");
	}

	public User(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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
}

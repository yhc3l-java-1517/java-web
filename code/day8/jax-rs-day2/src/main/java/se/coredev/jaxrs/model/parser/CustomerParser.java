package se.coredev.jaxrs.model.parser;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import se.coredev.jaxrs.model.Customer;

public final class CustomerParser {

	private CustomerParser() {
	}

	public static Customer fromXmlString(String xml) {

		try {
			Element root = new Builder().build(xml, null).getRootElement();
			Long id = Long.parseLong(root.getFirstChildElement("id").getValue());
			String firstName = root.getFirstChildElement("firstName").getValue();
			String lastName = root.getFirstChildElement("lastName").getValue();
			String customerNumber = root.getFirstChildElement("customerNumber").getValue();

			return new Customer(id, firstName, lastName, customerNumber);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String asXmlString(Customer customer) {
		try {
			Element root = new Element("customer");
			root.appendChild(createElement("id", customer.getId().toString()));
			root.appendChild(createElement("firstName", customer.getFirstName()));
			root.appendChild(createElement("lastName", customer.getLastName()));
			root.appendChild(createElement("customerNumber", customer.getCustomerNumber()));

			return new Document(root).toXML();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Element createElement(String name, String value) {
		Element element = new Element(name);
		element.appendChild(value);
		return element;
	}

	// 1001;Luke;Skywalker;LW-1001
	public static Customer fromString(String value) {

		final String[] parts = value.split(";");
		Long id = Long.parseLong(parts[0]);
		String firstName = parts[1];
		String lastName = parts[2];
		String customerNumber = parts[3];

		return new Customer(id, firstName, lastName, customerNumber);
	}

	public static String asString(Customer customer) {
		return new StringBuilder().append(customer.getId())
		                          .append(";")
		                          .append(customer.getFirstName())
		                          .append(";")
		                          .append(customer.getLastName())
		                          .append(";")
		                          .append(customer.getCustomerNumber())
		                          .toString();
	}
}

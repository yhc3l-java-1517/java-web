package se.coredev.jaxrs.web;

import static se.coredev.jaxrs.model.parser.CustomerParser.asString;
import static se.coredev.jaxrs.model.parser.CustomerParser.asXmlString;
import static se.coredev.jaxrs.model.parser.CustomerParser.fromString;
import static se.coredev.jaxrs.model.parser.CustomerParser.fromXmlString;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.repository.InMemoryCustomerRepository;
import se.coredev.jaxrs.service.CustomerService;

@Path("/customer")
public final class CustomerWebService {

	private final static CustomerService service = new CustomerService(new InMemoryCustomerRepository());

	@Context
	private UriInfo uriInfo;
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response createCustomer(String value, @HeaderParam("content-type") String contentType) {

		Customer customer = contentType.equals(MediaType.APPLICATION_XML) ? fromXmlString(value) : fromString(value);
		customer = service.createCustomer(customer);

		URI location = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getCustomer").build(customer.getId());
//		URI location = uriInfo.getAbsolutePathBuilder().path(customer.getId().toString()).build();
		
		return Response.created(location).build();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response getCustomer(@PathParam("id") Long id, @HeaderParam("accept") String accept) {

		Customer customer = service.getCustomer(id);

		if (customer == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		String result = accept.equals(MediaType.APPLICATION_XML) ? asXmlString(customer) : asString(customer);

		return Response.ok(result).build();
	}
}

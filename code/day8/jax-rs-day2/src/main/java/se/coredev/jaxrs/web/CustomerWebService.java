package se.coredev.jaxrs.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import se.coredev.jaxrs.model.Customer;
import se.coredev.jaxrs.repository.InMemoryCustomerRepository;
import se.coredev.jaxrs.service.CustomerService;
import static se.coredev.jaxrs.model.parser.CustomerParser.*;



@Path("/customer")
public final class CustomerWebService {

	private final static CustomerService service = new CustomerService(new InMemoryCustomerRepository());

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response createCustomer(String value) {
		
		Customer customer = fromString(value);
		customer = service.createCustomer(customer);
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCustomerAsXml(@PathParam("id") Long id) {
		
		Customer customer = service.getCustomer(id);
		
		if(customer == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(asXmlString(customer)).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCustomer(@PathParam("id") Long id) {
		
		Customer customer = service.getCustomer(id);
		
		if(customer == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(asString(customer)).build();
	}
	
}

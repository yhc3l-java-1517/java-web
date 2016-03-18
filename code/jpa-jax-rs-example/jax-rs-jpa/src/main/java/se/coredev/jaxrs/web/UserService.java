package se.coredev.jaxrs.web;

import static se.coredev.jaxrs.ContextLoader.getBean;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import se.coredev.jaxrs.model.User;
import se.coredev.jpa.model.UserData;
import se.coredev.jpa.repository.UserDataRepository;

@Path("/users")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public final class UserService {

	@Context
	private UriInfo uriInfo;
 
	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") Long id) {

		UserData userData = getBean(UserDataRepository.class).findOne(id);

		if (userData == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}

		return new User(userData.getId(), userData.getFirstName(), userData.getLastName());
	}

}

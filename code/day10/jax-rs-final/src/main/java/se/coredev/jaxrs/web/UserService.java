package se.coredev.jaxrs.web;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import se.coredev.jaxrs.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserService {

	private static final Map<Long, User> users = new HashMap<>();
	private static final AtomicLong ids = new AtomicLong(1000);

	static {
		Long id = ids.incrementAndGet();
		users.put(id, new User(id, "Master", "Yoda", Arrays.asList("Admin", "Super")));		
	}
	
	@Context
	private UriInfo uriInfo;

	@POST
	public Response addUser(User user) {

		Long id = ids.getAndIncrement();
		users.put(id, new User(id, user.getFirstName(), user.getLastName(), user.getRoles()));

		URI location = uriInfo.getAbsolutePathBuilder().path(getClass(), "getUser").build(id);

		return Response.created(location).build();
	}

//	@GET
//	public Response getAllUsers(){
//		Collection<User> result = users.values();
//		GenericEntity<Collection<User>> entity = new GenericEntity<Collection<User>>(result){};
//		return Response.ok(entity).build();
//	}
	
	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") Long id) {

		if (users.containsKey(id)) {
			return users.get(id);
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

}

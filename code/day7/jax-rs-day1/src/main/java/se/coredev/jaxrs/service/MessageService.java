package se.coredev.jaxrs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/message")
public final class MessageService {

	private static final Map<Long, String> messages = new HashMap<Long, String>();
	private static final AtomicLong ids = new AtomicLong(1000);

	// Create /message
	@POST
	public Response createMessage(String message) {

		final Long id = ids.incrementAndGet();
		messages.put(id, message);

		return Response.status(Status.CREATED).header("Location", "message/" + id).build();
	}

	// Read /message/1001
	@GET
	@Path("{id}")
	public Response getMessage(@PathParam("id") Long id) {

		if (messages.containsKey(id)) {
			return Response.ok(messages.get(id)).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	// Update
	@PUT
	@Path("{id}")
	public Response updateMessage(@PathParam("id") Long id, String message) {

		if (messages.containsKey(id)) {
			messages.put(id, message);
			return Response.noContent().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	// Delete
	@DELETE
	@Path("{id}")
	public Response deleteMessage(@PathParam("id") Long id) {
		if (messages.containsKey(id)) {
			messages.remove(id);
			return Response.noContent().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}

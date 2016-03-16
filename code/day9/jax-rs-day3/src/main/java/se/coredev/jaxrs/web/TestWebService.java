package se.coredev.jaxrs.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import se.coredev.jaxrs.web.exception.BadMessageException;

@Path("/test")
public final class TestWebService {

	@Context
	private HttpHeaders httpHeaders;

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("message/{id}")
	public String getMessage(@PathParam("id") String messageId) {

		if ("1001".equals(messageId)) {
			return "Hello!";
		}

		throw new WebApplicationException(404);
	}

	@POST
	@Path("message")
	public void addMessage(String message) {
		if (message.equals("darth")) {
			throw new BadMessageException(message);
		}
	}

	@GET
	@Path("uri-builder")
	public String testUriBuilder() {
		StringBuilder result = new StringBuilder();

		result.append("Path:").append(uriInfo.getPath());
		result.append("\nAbsolute path:").append(uriInfo.getAbsolutePath());
		result.append("\nBase uri:").append(uriInfo.getBaseUri());
		result.append("\nRequest uri:").append(uriInfo.getRequestUri());
		result.append("\nPath segments:").append(uriInfo.getPathSegments());

		return result.toString();
	}

	@GET
	@Path("uri-info")
	public String testUriInfo() {
		StringBuilder result = new StringBuilder();
		uriInfo.getQueryParameters().entrySet().forEach(e -> result.append("name: ")
		                                                           .append(e.getKey())
		                                                           .append(" value:")
		                                                           .append(e.getValue())
		                                                           .append("\n"));
		return result.toString();
	}

	@GET
	@Path("headers")
	public String testHttpHeaders() {
		return httpHeaders.getHeaderString("api-key");
	}

}

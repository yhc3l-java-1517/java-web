package se.coredev.jaxrs.web.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public final class BadMessageException extends WebApplicationException{

	private static final long serialVersionUID = 5110216245203788814L;

	public BadMessageException(String badMessage){
		super(Response.status(Status.BAD_REQUEST).entity("Bad message detected:" + badMessage).build());
	}
	
}

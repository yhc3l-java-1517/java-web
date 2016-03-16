package se.coredev.jaxrs.web.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import se.coredev.jaxrs.service.exception.ServiceException;

@Provider
public final class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(ServiceException exception) {
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
	}

}

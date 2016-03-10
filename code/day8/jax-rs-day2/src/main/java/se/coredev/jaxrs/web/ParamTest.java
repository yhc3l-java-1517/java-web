package se.coredev.jaxrs.web;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/parameter")
public final class ParamTest {

	// /parameter?value=hello
	@GET
	public String echo(@QueryParam("value") @DefaultValue("No value") String value){
		return "Value: " + value;
	}
	
}

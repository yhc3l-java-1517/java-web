package se.coredev.jaxrs.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTextMessage() {
		return "Hello!";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlMessage() {
		return "<b>Message</b>";
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void addValue(String value) {
		System.out.println("Plain:" + value);
	}

	@POST
	@Consumes(MediaType.TEXT_HTML)
	public void addHtml(String html) {
		System.out.println("HTML: " + html);
	}

}

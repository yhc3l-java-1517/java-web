package se.coredev.web;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/message/*")
public final class MessageService extends HttpServlet {

	private static final long serialVersionUID = -2702896852379697005L;

	private static final AtomicInteger messageIds = new AtomicInteger(1000);
	private static final Map<Integer, String> messages = new HashMap<>();

	// .../message 		- POST 201/400 		C
	// .../message/{id} - GET 200/404 		R
	// .../message/{id} - PUT 204/404 		U
	// .../message/{id} - DELETE 204/404 	D

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getPathInfo().split("/")[1];
		String message = messages.get(Integer.parseInt(id));

		if (message == null) {
			response.setStatus(SC_NOT_FOUND);
		}
		else {
			response.getWriter().print(message);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message = IOUtils.toString(request.getInputStream());
		
		if (message.trim().equals("")) {
			response.setStatus(SC_BAD_REQUEST);
		}
		else {
			Integer id = messageIds.incrementAndGet();
			messages.put(id, message);
			response.setStatus(SC_CREATED);
			response.setHeader("Location", request.getRequestURL().toString() + "/" + id);
		}
	}

}

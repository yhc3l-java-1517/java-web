package se.coredev.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 2645697421586201301L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = getServletConfig().getInitParameter("default-message");
		String global = getServletContext().getInitParameter("global-message");
		response.getWriter().print("Message:" + message);
		response.getWriter().print("Global:" + global);
	}

}

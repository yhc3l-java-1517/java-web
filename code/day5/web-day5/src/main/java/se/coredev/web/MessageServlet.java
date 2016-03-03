package se.coredev.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/message")
public final class MessageServlet extends HttpServlet{

	private static final long serialVersionUID = 4124700350495052756L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Hello!");
		DataSource ds = (DataSource) getServletContext().getAttribute(ApplicationInitializer.DATASOURCE_KEY);		
	}
}

// *ServletContextListener
// *ServletRequestListener

// ServletRequestAttributeListener
// ServletContextAttributeListener
// HttpSessionListener
// HttpSessionAttributeListener
// HttpSessionIdListener
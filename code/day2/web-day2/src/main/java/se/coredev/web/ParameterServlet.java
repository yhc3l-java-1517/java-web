package se.coredev.web;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/parameters")
public class ParameterServlet extends HttpServlet {

	private static final long serialVersionUID = 2052988666118305074L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getQueryString() == null) {
			response.setStatus(SC_BAD_REQUEST);
			
		} else {
			
			response.setContentType("text/plain");
			
			 for(Entry<String, String[]> parameter : request.getParameterMap().entrySet()){
				 response.getWriter().println(getParameter(parameter));
			 }			
		}
	}

	private String getParameter(Entry<String, String[]> parameter) {
		StringBuilder result = new StringBuilder();
		
		result.append("\nparameter-name:").append(parameter.getKey());
		result.append("\nparameter-value:").append(asList(parameter.getValue()).stream().collect(joining(", ")));		
		
		return result.toString();
	}

}













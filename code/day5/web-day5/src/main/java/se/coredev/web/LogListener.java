package se.coredev.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class LogListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		final HttpServletRequest httpRequest = (HttpServletRequest) event.getServletRequest();
		log(httpRequest);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		System.out.println("Request destroyed");
	}
	
	private static void log(HttpServletRequest httpRequest) {
		String message = new StringBuilder().append("\nRequest URL:")
		                                    .append(httpRequest.getRequestURL())
		                                    .append("\nMethod:")
		                                    .append(httpRequest.getMethod())
		                                    .toString();
		System.out.println(message);
	}
}

package se.coredev.web;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class LogListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		final HttpServletRequest httpRequest = (HttpServletRequest) event.getServletRequest();
		final String log = new StringBuilder().append(httpRequest.getRequestURL())
		                                      .append("\n")
		                                      .append(httpRequest.getMethod())
		                                      .append("\n")
		                                      .append(new Date().toString())
		                                      .append("\n")
		                                      .toString();
		System.out.println(log);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

}

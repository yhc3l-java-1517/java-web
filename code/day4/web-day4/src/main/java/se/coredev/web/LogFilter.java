package se.coredev.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "log-filter")
public final class LogFilter implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		log(httpRequest);
		chain.doFilter(request, response);
	}

	private static void log(HttpServletRequest httpRequest) {
		String message = new StringBuilder().append("\nRequest URL:")
		                                    .append(httpRequest.getRequestURL())
		                                    .append("\nMethod:")
		                                    .append(httpRequest.getMethod())
		                                    .toString();
		System.out.println(message);
	}

	@Override
	public void destroy() {
	}

}

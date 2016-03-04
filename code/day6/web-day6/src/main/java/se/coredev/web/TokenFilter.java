package se.coredev.web;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

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

@WebFilter("/quote/*")
public final class TokenFilter implements Filter{

	private static final String AUTH_TOKEN_HEADER_KEY = "auth-token";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		final String authToken = httpRequest.getHeader(AUTH_TOKEN_HEADER_KEY);
		
		if("yoda".equals(authToken)) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			httpResponse.setStatus(SC_UNAUTHORIZED);
		}
	}

	@Override
	public void destroy() {
	}

}

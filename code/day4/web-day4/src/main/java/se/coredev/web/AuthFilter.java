package se.coredev.web;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

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

@WebFilter(filterName = "auth-filter")
public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if("yoda".equals(httpRequest.getHeader("secret"))){
			chain.doFilter(request, response);
		} else {
			httpResponse.setStatus(SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {}

}

package se.coredev.web;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/quote/*")
public final class QuoteService extends HttpServlet {

	private static final long serialVersionUID = 1844217359406575005L;
	private static Map<Integer, String> quotes = new HashMap<>();
	private static final AtomicInteger ids = new AtomicInteger(1000);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final Integer id = extractId(request);

		if (id > 0) {
			if (quotes.containsKey(id)) {
				response.getWriter().println(quotes.get(id));
			}
			else {
				response.setStatus(SC_NOT_FOUND);
			}
		}
		else {
			final PrintWriter writer = response.getWriter();
			quotes.values().stream().forEach(q -> writer.print(q + "\n"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String quote = extractQuote(request);

		if (quote.trim().length() == 0) {
			response.setStatus(SC_BAD_REQUEST);
		}
		else {
			final Integer id = ids.getAndIncrement();
			quotes.put(id, quote);
			response.setHeader("Location", request.getRequestURL().append("/").append(id).toString());
			response.setStatus(SC_CREATED);
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String quote = extractQuote(request);
		final Integer id = extractId(request);

		if (quote.trim().length() == 0) {
			response.setStatus(SC_BAD_REQUEST);
		}
		else {
			quotes.put(id, quote);
			response.setStatus(SC_NO_CONTENT);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Integer id = extractId(request);

		if (quotes.containsKey(id)) {
			quotes.remove(id);
		}
		else {
			response.setStatus(SC_NOT_FOUND);
		}
	}

	private static String extractQuote(HttpServletRequest request) throws IOException {
		return IOUtils.toString(request.getInputStream());
	}

	private Integer extractId(HttpServletRequest request) {

		final String uri = request.getRequestURI();
		final String path = StringUtils.removeEnd(uri, "/");
		final String idValue = StringUtils.substringAfter(path, "quote/");

		return idValue.trim().length() == 0 ? -1 : Integer.parseInt(idValue);
	}

}

package com.rays.pro4.controller;

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
import javax.servlet.http.HttpSession;

import com.rays.pro4.Util.ServletUtility;

/**
 * The Class Filter implementation. Handles session validation for incoming
 * requests. If the user session is not found, redirects to the login view.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class FrontControler implements Filter {

	/**
	 * Performs filtering tasks on every request. If the session is expired or user
	 * is not logged in, redirects to the login page. Otherwise, continues the
	 * filter chain.
	 *
	 * @param req   the ServletRequest object
	 * @param resp  the ServletResponse object
	 * @param chain the FilterChain to pass control to the next filter or resource
	 * @throws IOException      if an input or output error occurs
	 * @throws ServletException if the request could not be handled
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Fctl Do filter");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			ServletUtility.setErrorMessage(" Your Session has been Expired... Please Login Again", request);
			String str = request.getRequestURI();
			request.setAttribute("uri", str);
			System.out.println("URI" + str);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			return;
		} else {
			chain.doFilter(req, resp);
		}
	}

	/**
	 * Initializes the filter.
	 *
	 * @param conf the FilterConfig object containing configuration information
	 * @throws ServletException if an exception occurs that interferes with the
	 *                          filter's normal operation
	 */
	public void init(FilterConfig conf) throws ServletException {
	}

	/**
	 * Destroys the filter and releases resources.
	 */
	public void destroy() {
	}
}
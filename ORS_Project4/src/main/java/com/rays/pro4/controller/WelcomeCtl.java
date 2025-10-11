package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rays.pro4.Util.ServletUtility;

/**
 * Welcome functionality Controller.
 * <p>
 * Performs operation for showing the Welcome page after login.
 * </p>
 * 
 * @author Pushpraj Singh Kachhaway
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * Contains Display logics.
	 * <p>
	 * This method forwards the request to the Welcome view page.
	 * </p>
	 *
	 * @param request  the {@link HttpServletRequest} object containing client
	 *                 request
	 * @param response the {@link HttpServletResponse} object for sending response
	 *                 to the client
	 * 
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an input or output error is detected
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("WelcomeCtl Method doGet Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("WelcomeCtl Method doGet Ended");
	}

	/**
	 * Returns the view page path for Welcome screen.
	 *
	 * @return the string constant of the Welcome view path
	 */
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}
}
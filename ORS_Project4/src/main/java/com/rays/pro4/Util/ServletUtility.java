package com.rays.pro4.Util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.controller.BaseCtl;
import com.rays.pro4.controller.ORSView;

/**
 * This class provides utility operation for Servlet container like forward,
 * redirect, handle generic exception, manage success and error message, manage
 * default Bean and List, manage pagination parameters.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class ServletUtility {

	/**
	 * Forwards request and response to the given page.
	 *
	 * @param page     JSP/Servlet page to forward to
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * Redirects response to the given page.
	 *
	 * @param page     URL to redirect to
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	/**
	 * Returns the error message from request attributes.
	 *
	 * @param property Name of the error attribute
	 * @param request  HttpServletRequest object
	 * @return Error message string or empty if not found
	 */
	public static String getErrorMessage(String property, HttpServletRequest request) {

		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Returns a general message from request attributes.
	 *
	 * @param property Name of the message attribute
	 * @param request  HttpServletRequest object
	 * @return Message string or empty if not found
	 */
	public static String getMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets error message in request attributes.
	 *
	 * @param msg     Error message
	 * @param request HttpServletRequest object
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * Returns the error message from request (BaseCtl.MSG_ERROR).
	 *
	 * @param request HttpServletRequest object
	 * @return Error message string or empty if not found
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets success message in request attributes.
	 *
	 * @param msg     Success message
	 * @param request HttpServletRequest object
	 */
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * Returns the success message from request (BaseCtl.MSG_SUCCESS).
	 *
	 * @param request HttpServletRequest object
	 * @return Success message string or empty if not found
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a bean in request attributes.
	 *
	 * @param bean    BaseBean object
	 * @param request HttpServletRequest object
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	/**
	 * Returns a bean from request attributes.
	 *
	 * @param request HttpServletRequest object
	 * @return BaseBean object or null if not set
	 */
	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	/**
	 * Returns a request parameter value.
	 *
	 * @param property Parameter name
	 * @param request  HttpServletRequest object
	 * @return Parameter value or empty string if null
	 */
	public static String getParameter(String property, HttpServletRequest request) {
		String val = (String) request.getParameter(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a list in request attributes.
	 *
	 * @param list    List object
	 * @param request HttpServletRequest object
	 */
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	/**
	 * Returns a list from request attributes.
	 *
	 * @param request HttpServletRequest object
	 * @return List object or null if not set
	 */
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	/**
	 * Sets page number in request attributes.
	 *
	 * @param pageNo  Page number
	 * @param request HttpServletRequest object
	 */
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	/**
	 * Returns page number from request attributes.
	 *
	 * @param request HttpServletRequest object
	 * @return Page number as integer
	 */
	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}

	/**
	 * Sets page size in request attributes.
	 *
	 * @param pageSize Page size
	 * @param request  HttpServletRequest object
	 */
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * Returns page size from request attributes.
	 *
	 * @param request HttpServletRequest object
	 * @return Page size as integer
	 */
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}

	/**
	 * Handles exception by setting it in request attributes and redirecting to
	 * error page.
	 *
	 * @param e        Exception object
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);
	}
}
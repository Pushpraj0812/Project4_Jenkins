package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.CollegeBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Model.CollegeModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

/**
 * ChangePasswordCtl is a servlet controller responsible for handling change
 * password functionality. It performs validation, populates the bean, and calls
 * the UserModel to change the user's password. It extends the BaseCtl class to
 * reuse common controller functionality.
 * 
 * Mapped to the URL pattern "/ChangePasswordCtl".
 * 
 * @author Pushpraj Singh Kachhaway
 */

@WebServlet(name = "CollegeListCtl", urlPatterns = { "/ctl/CollegeListCtl" })
public class CollegeListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CollegeListCtl.class);

	/**
	 * Preloads the list of colleges into the request scope.
	 *
	 * @param request the {@link HttpServletRequest} object
	 * @author Pushpraj Singh Kachhaway
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel cmodel = new CollegeModel();
		try {
			List CollegeList = cmodel.list();
			request.setAttribute("CollegeList", CollegeList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Populates a {@link CollegeBean} from request parameters.
	 *
	 * @param request the {@link HttpServletRequest} object
	 * @return a populated {@link CollegeBean}
	 * @author Pushpraj Singh Kachhaway
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();
		System.out.println("college name id: " + request.getParameter("collegeId"));
		bean.setId(DataUtility.getLong(request.getParameter("collegeId")));
		System.out.println("college bean id: " + bean.getId());
		bean.setId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));

		return bean;
	}

	/**
	 * Handles HTTP GET requests. Displays the list of colleges with pagination
	 * support.
	 *
	 * @param request  the {@link HttpServletRequest} object
	 * @param response the {@link HttpServletResponse} object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 * @author Pushpraj Singh Kachhaway
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CollegeBean bean = (CollegeBean) populateBean(request);
		CollegeModel model = new CollegeModel();

		try {
			List<CollegeBean> list = model.search(bean, pageNo, pageSize);
			List<CollegeBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("next", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Handles HTTP POST requests. Performs operations like search, pagination, new
	 * record redirection, deletion, reset, and back navigation.
	 *
	 * @param request  the {@link HttpServletRequest} object
	 * @param response the {@link HttpServletResponse} object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 * @author Pushpraj Singh Kachhaway
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CollegeListCtl doPost Start");

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		CollegeModel model = new CollegeModel();
		CollegeBean bean = (CollegeBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CollegeBean deletebean = new CollegeBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
				return;

			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("next", next.size());

			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the view path for College list.
	 *
	 * @return the view identifier as a {@link String}
	 * @author Pushpraj Singh Kachhaway
	 */
	@Override
	protected String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}
}
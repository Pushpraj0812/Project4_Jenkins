package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.DoctorBean;
import com.rays.pro4.Model.DoctorModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "DoctorCtl", urlPatterns = { "/DoctorCtl" })
public class DoctorCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
		
		DoctorModel model = new DoctorModel();

		try {

			List rlist = model.list();
			request.setAttribute("rlist", rlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Name"))) {
			request.setAttribute("Name", "Name must contains alphabet only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Mobile"))) {
			request.setAttribute("Mobile", PropertyReader.getValue("error.require", "Mobile"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("Mobile"))) {
			request.setAttribute("Mobile", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Experties"))) {
			request.setAttribute("Experties", PropertyReader.getValue("error.require", "Experties"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		DoctorBean bean = new DoctorBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setMobile(DataUtility.getString(request.getParameter("Mobile")));
		bean.setExperties(DataUtility.getString(request.getParameter("Experties")));

		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		DoctorModel model = new DoctorModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("product Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			DoctorBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		DoctorModel model = new DoctorModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DOCTOR_LIST_CTL, request, response);

		}
		if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DOCTOR_CTL, request, response);

		}

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			DoctorBean bean = (DoctorBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Doctor is successfully Updated", request);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					System.out.println("product not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);

					ServletUtility.setSuccessMessage("Doctor is successfully Added", request);
					ServletUtility.setBean(bean, request);

					bean.setId(pk);
					ServletUtility.forward(getView(), request, response);
				} catch (Exception e) {
					System.out.println("Doctor not added");
					e.printStackTrace();
				}

			}

		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.DOCTOR_VIEW;
	}
}
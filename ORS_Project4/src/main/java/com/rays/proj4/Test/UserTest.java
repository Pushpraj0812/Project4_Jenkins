package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.UserBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.UserModel;

/**
 * User Model Test classes.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class UserTest {

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {

		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByLogin();
		// testSearch();
		// authenticate();
	}

	private static void testUpdate() throws DuplicateRecordException {
		try {
			UserBean bean = new UserBean();
			UserModel model = new UserModel();
			bean.setId(34);
			bean.setFirstName("delete");
			bean.setLastName("goyal");
			model.update(bean);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testFindByPk() {
		try {
			UserBean bean = new UserBean();
			long pk = 5L;
			UserModel model = new UserModel();
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test find by pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByLogin() {
		try {
			UserBean bean = new UserBean();
			UserModel model = new UserModel();
			bean = model.findByLogin("solankiprakhar11@gmail.com");
			if (bean == null) {
				System.out.println("Test findByLogin fail");
			}
			System.out.println(bean.getId());

			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean.setId(34);
		model.delete(bean);
		System.out.println("Data De;eted Succ");
	}

	private static void testSearch() {
		try {
			UserBean bean = new UserBean();
			UserModel model = new UserModel();
			List list = new ArrayList();
			bean.setFirstName("Pushpraj");

			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId());
				System.out.print(bean.getFirstName());
				System.out.print(bean.getLastName());
				System.out.print(bean.getLogin());
				System.out.print(bean.getPassword());
				System.out.print(bean.getDob());
				System.out.print(bean.getRoleId());
				System.out.print(bean.getGender());
				System.out.println();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void authenticate() {
		try {
			UserBean bean = new UserBean();
			UserModel model = new UserModel();
			bean.setLogin("roshani@gmail.com");
			bean.setPassword("Roshani@123");
			bean = model.authenticate(bean.getLogin(), bean.getPassword());
			if (bean != null) {
				System.out.println("Successfully login");
			} else {
				System.out.println("Invaliad login Id & password");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
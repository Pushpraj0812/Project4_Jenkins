package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.DoctorBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.DoctorModel;

public class DoctorTest {

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException, ParseException {

		// testnextPK();
		// testFindByPk();
		// testadd();
		// testUpdate();
		// testDelete();
		// testSearch();
		 testList();
	}

	public static DoctorModel model = new DoctorModel();

	private static void testnextPK() {

		int pk = 0;
		try {
			pk = model.nextPK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("next pk " + pk);
	}

	private static void testFindByPk() {

		DoctorBean bean = new DoctorBean();

		long pk = 15;
		bean = model.findByPK(pk);

		if (bean == null) {
			System.out.println("Test find by pk fail");
		}

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getMobile());

	}

	private static void testadd() throws ParseException {

		DoctorBean bean = new DoctorBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		bean.setName("test");
		bean.setMobile("7878787878");
		bean.setExperties("fffff");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = model.add(bean);
	}

	public static void testUpdate() {

		DoctorBean bean = model.findByPK(15);

		bean.setName("dgygb");
		model.update(bean);

	}

	private static void testDelete() {

		DoctorBean bean = new DoctorBean();

		bean.setId(16);
		model.delete(bean);
	}

	private static void testSearch() {

		DoctorBean bean = new DoctorBean();
		List list = new ArrayList();

		// bean.setName("Dr. Priya Singh");

		list = model.search(bean, 1, 10);
		if (list.size() < 0) {
			System.out.println("Test search fail");
		}

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (DoctorBean) it.next();

			System.out.print(bean.getId());
			System.out.print(bean.getName());
			System.out.print(bean.getMobile());
			System.out.println();
		}
	}

	private static void testList() {
		try {
			DoctorModel model = new DoctorModel(); // make sure you have your model instance
			List list = model.list();

			if (list.size() == 0) {
				System.out.println("No records found");
			} else {
				System.out.println("Doctor List:");
				for (Object obj : list) {
					DoctorBean bean = (DoctorBean) obj;
					System.out.print("ID: " + bean.getId() + ", ");
					System.out.print("Name: " + bean.getName() + ", ");
					System.out.print("Mobile: " + bean.getMobile() + ", ");
					System.out.print("Experties: " + bean.getExperties());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.CollegeBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.CollegeModel;

/**
 * College Model Test classes.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class CollegeTest {

	public static void main(String[] args) throws Exception {

		// testnextpk();
		// testAdd();
		testDelete();
		// searchFindByName();
		// searchFindByPk();
		// update();
		// search();

	}

	private static void testnextpk() throws DatabaseException {

		CollegeModel model = new CollegeModel();

		int pk = model.nextPK();
		System.out.println("next pk " + pk);
	}

	private static void search() throws Exception {
		try {
			CollegeBean bean = new CollegeBean();
			CollegeModel model = new CollegeModel();
			List list = new ArrayList();
			// bean.setName("IIT Kharangpur");

			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			} else {
				System.out.println("Search Results:");
			}

			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.print("\t" + bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getAddress());
				System.out.print("\t" + bean.getState());
				System.out.print("\t" + bean.getCity());
				System.out.print("\t" + bean.getPhoneNo());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getModifiedDatetime());
				System.out.println();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void update() {
		try {
			CollegeBean bean = new CollegeBean();
			bean.setName("AU University");
			bean.setAddress("Lonavala");
			bean.setId(17);
			CollegeModel model = new CollegeModel();
			model.update(bean);
			System.out.println("Record Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void searchFindByPk() {

		try {

			CollegeModel model = new CollegeModel();

			CollegeBean bean = model.findByPK(16);
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void searchFindByName() {
		try {

			CollegeModel model = new CollegeModel();

			CollegeBean bean = model.findByName("MIST");
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testDelete() {

		try {
			CollegeBean bean = new CollegeBean();
			long pk = 17L;
			bean.setId(pk);
			CollegeModel model = new CollegeModel();
			model.delete(bean);
			CollegeBean deletebean = model.findByPK(pk);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() throws DuplicateRecordException {
		try {
			CollegeBean bean = new CollegeBean();
			bean.setName("lll");
			bean.setAddress("lll");
			bean.setState("mp");
			bean.setCity("uuu");
			bean.setPhoneNo("7878787878");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			CollegeModel model = new CollegeModel();
			long pk = model.add(bean);
			System.out.println("Test Add succ");
			CollegeBean addedBean = model.findByPK(pk);
			if (addedBean == null) {
				System.out.println("Test ass fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}

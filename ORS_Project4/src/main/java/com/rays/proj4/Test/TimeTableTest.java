package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.TimeTableBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.TimeTableModel;

/**
 * TimeTable Model Test classes.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class TimeTableTest {

	public static TimeTableModel model = new TimeTableModel();

	public static void main(String[] args) throws Exception {

		// testNextPk();
		// testadd();
		// testdelete();
		// testupdate();
		// testfindBypk();
		testsearch();

	}

	private static void testNextPk() throws DatabaseException {

		int pk = model.nextPK();
		System.out.println("next pk " + pk);
	}

	public static void testadd() {
		try {
			TimeTableBean bean = new TimeTableBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			bean.setCourseId(5);
			bean.setCourseName("BBA");
			bean.setSubjectId(4);
			bean.setSubjectName("Account");
			bean.setSemester("5");
			bean.setExamDate(sdf.parse("22/09/2021"));
			bean.setExamTime("10 am to 1 pm");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.add(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testdelete() {
		try {
			TimeTableBean bean = new TimeTableBean();
			long pk = 32;

			bean.setId(pk);
			model.delete(bean);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testupdate() throws ParseException, DuplicateRecordException {
		try {
			TimeTableBean bean = new TimeTableBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			bean.setId(31);
			bean.setCourseId(4);
			bean.setCourseName("Pharma");
			bean.setSubjectId(8);
			bean.setSubjectName("Contemporary Social Concerns");
			bean.setExamTime("1 to 4 pm");
			bean.setExamDate(sdf.parse("22/08/2021"));
			model.update(bean);
			;
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindBypk() {
		try {
			TimeTableBean bean = new TimeTableBean();

			bean = model.findByPK(3);
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getSemester());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() throws ApplicationException {

		TimeTableBean bean = new TimeTableBean();

		// bean.setSubjectName("HRM");

		List list = model.search(bean, 0, 0);

		Iterator it = list.iterator();

		if (list.size() < 0) {
			System.out.println("test search fail");
		}

		while (it.hasNext()) {

			bean = (TimeTableBean) it.next();
			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
		}
	}

}
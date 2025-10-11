package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.CourseBean;
import com.rays.pro4.Bean.SubjectBean;
import com.rays.pro4.Bean.TimeTableBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

/**
 * JDBC implementation of the TimeTable Model. Provides CRUD operations, search,
 * and validation methods for TimeTableBean.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class TimeTableModel {

	private static Logger log = Logger.getLogger(TimeTableModel.class);

	/**
	 * Returns the next primary key of the timetable table.
	 * 
	 * @return next primary key as Integer
	 * @throws DatabaseException if there is a database access error
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_timetable");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Adds a new timetable entry.
	 * 
	 * @param bean TimeTableBean containing timetable details
	 * @return primary key of the newly added timetable entry
	 * @throws Exception if any database or application-level exception occurs
	 */
	public long add(TimeTableBean bean) throws Exception {

		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		CourseModel courseModel = new CourseModel();
		CourseBean CourseBean = courseModel.FindByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectModel subjectmodel = new SubjectModel();
		SubjectBean subjectBean = subjectmodel.FindByPK(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getSubjectName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT into st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubjectName());
			pstmt.setString(6, bean.getSemester());
			pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(8, bean.getExamTime());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			System.out.println("record inserted " + i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception....", e);
			System.out.println(e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Deletes a timetable entry.
	 * 
	 * @param bean TimeTableBean to delete
	 * @throws ApplicationException if a database-level error occurs
	 */
	public void delete(TimeTableBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_timetable where ID=?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println("record delete " + i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : delete Rollback Exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Timeteble");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete End");
	}

	/**
	 * Updates a timetable entry.
	 * 
	 * @param bean TimeTableBean with updated data
	 * @throws ApplicationException     if a database-level error occurs
	 * @throws DuplicateRecordException if duplicate record exists
	 */
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.FindByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectModel smodel = new SubjectModel();
		SubjectBean SubjectBean = smodel.FindByPK(bean.getSubjectId());
		bean.setSubjectName(SubjectBean.getSubjectName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set course_id=?,course_name=?,subject_id=?,subject_name=?,semester=?,exam_date=?,exam_time=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getSemester());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getExamTime());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			pstmt.executeUpdate();
			System.out.println("timetable update");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception....", e);
			try {
				conn.rollback();

			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Finds a timetable entry by primary key.
	 * 
	 * @param pk primary key of timetable entry
	 * @return TimeTableBean if found, null otherwise
	 * @throws ApplicationException if a database-level error occurs
	 */
	public TimeTableBean findByPK(long pk) throws ApplicationException {

		log.debug("Model findBypk started");

		StringBuffer sql = new StringBuffer("select * from st_timetable where id=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception .....", e);
			throw new ApplicationException("Exception : Exception in getting by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBypk End");
		return bean;
	}

	/**
	 * Searches timetable entries based on criteria in TimeTableBean with
	 * pagination.
	 * 
	 * @param bean     TimeTableBean containing search criteria
	 * @param pageNo   page number
	 * @param pageSize number of records per page
	 * @return list of TimeTableBean objects matching the criteria
	 * @throws ApplicationException if a database-level error occurs
	 */
	public List<TimeTableBean> search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {

		System.out.println(" TimeTableModel.search() CALLED");

		StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" and course_id =" + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND and course_name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" and subject_id  =" + bean.getSubjectId());
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" and subject_name like '" + bean.getSubjectName() + "%'");
			}
			if (bean.getExamDate() != null && bean.getExamDate().getDate() > 0) {
				sql.append(" and exam_date like '" + new java.sql.Date(bean.getExamDate().getTime()) + "%'");
			}
			if (bean.getExamTime() != null && bean.getExamTime().length() > 0) {
				sql.append(" and exam_time like '" + bean.getExamTime() + "%'");
			}
		}

		System.out.println("Final SQL: " + sql.toString());

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception.....", e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	/**
	 * Checks if a timetable entry exists for a given semester.
	 * 
	 * @param CourseId  course id
	 * @param SubjectId subject id
	 * @param semester  semester
	 * @param ExamDate  exam date
	 * @return TimeTableBean if exists, null otherwise
	 */
	public static TimeTableBean checkBysemester(long CourseId, long SubjectId, String semester,
			java.util.Date ExamDate) {

		StringBuffer sql = new StringBuffer(
				"select * from st_timetable where course_id = ? and subject_id = ? and semester = ? and exam_date = ?");

		TimeTableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, CourseId);
			pstmt.setLong(2, SubjectId);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(ExamDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * Checks if a timetable entry exists for a course on a given date.
	 * 
	 * @param CourseId course id
	 * @param ExamDate exam date
	 * @return TimeTableBean if exists, null otherwise
	 */
	public static TimeTableBean checkByCourseName(long CourseId, java.util.Date ExamDate) {
		Connection conn = null;
		TimeTableBean bean = null;

		Date Exdate = new Date(ExamDate.getTime());

		StringBuffer sql = new StringBuffer("SELECT * FROM st_timetable WHERE COURSE_ID=? " + "AND EXAM_DATE=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			// ps.setDate(2, examdate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
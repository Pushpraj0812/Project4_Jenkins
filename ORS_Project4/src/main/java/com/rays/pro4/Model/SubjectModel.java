package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.CourseBean;
import com.rays.pro4.Bean.SubjectBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

/**
 * JDBC Implementation of Subject Model. Provides CRUD operations, search, and
 * list functionality for SubjectBean.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class SubjectModel {

	private static Logger log = Logger.getLogger(SubjectModel.class);

	/**
	 * Returns the next primary key of Subject table.
	 * 
	 * @return next primary key as Integer
	 * @throws DatabaseException if there is a database access error
	 */
	private Integer nextPK() throws DatabaseException {
		log.debug("Model nextpk Started");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM st_subject");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting pk");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model next pk End");
		return pk = pk + 1;
	}

	/**
	 * Adds a new subject to the database.
	 * 
	 * @param bean SubjectBean containing subject details
	 * @return primary key of the newly added subject
	 * @throws ApplicationException     if there is an application-level error
	 * @throws DuplicateRecordException if a subject with the same name exists
	 */
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.FindByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectBean duplicateName = findByName(bean.getCourseName());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Subject Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO st_subject VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getSubjectName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCourseName());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			log.error("Database Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				// ex.printStackTrace();
				throw new ApplicationException("Excetion : add rollback Exception " + ex.getMessage());
			}

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;

	}

	/**
	 * Deletes a subject from the database.
	 * 
	 * @param bean SubjectBean to delete
	 * @throws ApplicationException if there is an application-level error
	 */
	public void Delete(SubjectBean bean) throws ApplicationException {
		log.debug("Model Delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE  FROM st_subject WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback Wxception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in delete subjecte");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete End");

	}

	/**
	 * Updates subject details in the database.
	 * 
	 * @param bean SubjectBean containing updated details
	 * @throws ApplicationException     if there is an application-level error
	 * @throws DuplicateRecordException if a subject with the same name exists
	 */
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("model update Started");
		Connection conn = null;
		/*
		 * SubjectBean beanexist = findByName(bean.getSubjectName()); if(beanexist !=
		 * null && beanexist.getId() != bean.getId()) { throw new
		 * DuplicateRecordException("subject is already exist");
		 * 
		 * }
		 */

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.FindByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE st_subject SET SUBJECT_NAME=?,DESCRIPTION=?,COURSE_ID=?,COURSE_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getSubjectName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception,,,,,,,", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception :Exception in update subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model upodate End");

	}

	/**
	 * Finds a subject by its name.
	 * 
	 * @param name subject name to search
	 * @return SubjectBean if found, else null
	 * @throws ApplicationException if there is an application-level error
	 */
	public SubjectBean findByName(String name) throws ApplicationException {
		log.debug("Model findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_subject WHERE SUBJECT_NAME=?");
		SubjectBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			System.out.println(name + "jhdsfuhf");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("query working");
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(1);
				bean.setSubjectName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Model findByName End");
		}
		return bean;

	}

	/**
	 * Finds a subject by primary key.
	 * 
	 * @param pk primary key of the subject
	 * @return SubjectBean if found, else null
	 * @throws ApplicationException if there is an application-level error
	 */
	public SubjectBean FindByPK(long pk) throws ApplicationException {

		log.debug("Model FindByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_subject WHERE ID=?");
		Connection conn = null;
		SubjectBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception in getting subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Model FindbyPK End");
		}
		return bean;
	}

	/**
	 * Searches for subjects matching the given bean criteria.
	 * 
	 * @param bean SubjectBean with search criteria
	 * @return list of matching SubjectBean objects
	 * @throws DatabaseException    if there is a database-level error
	 * @throws ApplicationException if there is an application-level error
	 */
	public List search(SubjectBean bean) throws DatabaseException, ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Searches for subjects with pagination.
	 * 
	 * @param bean     SubjectBean with search criteria
	 * @param pageNo   page number
	 * @param pageSize number of records per page
	 * @return list of matching SubjectBean objects
	 * @throws DatabaseException    if there is a database-level error
	 * @throws ApplicationException if there is an application-level error
	 */
	public List search(SubjectBean bean, int pageNo, int pageSize) throws DatabaseException, ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("Select * from st_subject where 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
				System.out.println("NOT null");
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION  like '" + bean.getDescription() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			System.out.println(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception in the search" + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("MOdel search End");
		return list;
	}

	/**
	 * Lists all subjects.
	 * 
	 * @return list of SubjectBean objects
	 * @throws Exception if there is an application-level error
	 */
	public List list() throws Exception {
		return list(0, 0);
	}

	/**
	 * Lists subjects with pagination.
	 * 
	 * @param pageNo   page number
	 * @param pageSize number of records per page
	 * @return list of SubjectBean objects
	 * @throws Exception if there is an application-level error
	 */
	public List list(int pageNo, int pageSize) throws Exception {

		log.debug("model list started");

		List list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_subject");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " ," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			SubjectBean bean;
			while (rs.next()) {
				bean = new SubjectBean();

				bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

				list.add(bean);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception : Exception in getting list " + e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}

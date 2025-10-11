package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.CourseBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

/**
 * JDBC Implementation of CourseModel. Handles CRUD operations for Course
 * entities.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class CourseModel {

	private static Logger log = Logger.getLogger(CollegeModel.class);

	/**
	 * Gets the next primary key from the database.
	 *
	 * @return the next primary key
	 * @throws DatabaseException if there is a database access error
	 */
	private Integer nextPK() throws DatabaseException {
		log.debug("Model nextpk Started");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from  st_course");
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
	 * Adds a new Course.
	 *
	 * @param bean the CourseBean containing course details
	 * @return the generated primary key of the new course
	 * @throws ApplicationException     if an application-level error occurs
	 * @throws DuplicateRecordException if a duplicate course exists
	 */
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert st_course value(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			log.error("Database Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				// throw new ApplicationException("Excetion : add rollback Exception "
				// +ex.getMessage());
			}
			// throw new ApplicationException("Exception : Exception in add course" );
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;

	}

	/**
	 * Deletes a Course.
	 *
	 * @param bean the CourseBean containing course ID
	 * @throws ApplicationException if a database or application error occurs
	 */
	public void Delete(CourseBean bean) throws ApplicationException {
		log.debug("Model Delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id=?");
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
			throw new ApplicationException("Exception in delete course");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete End");

	}

	/**
	 * Finds a Course by name.
	 *
	 * @param name the course name
	 * @return the CourseBean if found, otherwise null
	 * @throws ApplicationException if a database error occurs
	 */
	public CourseBean findByName(String name) throws ApplicationException {
		log.debug("Model findByName Started");
		StringBuffer sql = new StringBuffer("select * from st_course where name=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(1);
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception in getting course by name");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Model findByName End");
		}
		return bean;

	}

	/**
	 * Finds a Course by primary key.
	 *
	 * @param pk the primary key
	 * @return the CourseBean if found, otherwise null
	 * @throws ApplicationException if a database error occurs
	 */
	public CourseBean FindByPK(long pk) throws ApplicationException {

		log.debug("Model FindByPK Started");
		StringBuffer sql = new StringBuffer("select * from st_course where id=?");
		Connection conn = null;
		CourseBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(1);
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception in getting course by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Model FindbyPK End");
		}
		return bean;
	}

	/**
	 * Updates an existing Course.
	 *
	 * @param bean the CourseBean containing updated details
	 * @throws ApplicationException     if a database error occurs
	 * @throws DuplicateRecordException if a duplicate course exists
	 */
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("model update Started");
		Connection conn = null;

		CourseBean beanExist = findByName(bean.getName());
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Course is alredy Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_course set name=?, description=?, duration=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updatingcourse");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Searches Courses based on the given criteria.
	 *
	 * @param bean the CourseBean containing search criteria
	 * @return the list of matching CourseBeans
	 * @throws DatabaseException    if a database access error occurs
	 * @throws ApplicationException if an application-level error occurs
	 */
	public List search(CourseBean bean) throws DatabaseException, ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Searches Courses with pagination.
	 *
	 * @param bean     the CourseBean containing search criteria
	 * @param pageNo   the page number
	 * @param pageSize the number of records per page
	 * @return the list of matching CourseBeans
	 * @throws DatabaseException    if a database access error occurs
	 * @throws ApplicationException if an application-level error occurs
	 */
	public List search(CourseBean bean, int pageNo, int pageSize) throws DatabaseException, ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND Description like '" + bean.getDescription() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND Duration like '" + bean.getDuration() + "%'");
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
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
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
	 * Gets the list of all Courses.
	 *
	 * @return the list of CourseBeans
	 * @throws Exception if a database error occurs
	 */
	public List list() throws Exception {
		return list(0, 0);
	}

	/**
	 * Gets the paginated list of Courses.
	 *
	 * @param pageNo   the page number
	 * @param pageSize the number of records per page
	 * @return the list of CourseBeans
	 * @throws Exception if a database error occurs
	 */
	public List list(int pageNo, int pageSize) throws Exception {

		log.debug("model list started");

		List list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_course");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " ," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			CourseBean bean;
			while (rs.next()) {
				bean = new CourseBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

				list.add(bean);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception : Exception in getting lidt " + e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.CollegeBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

/**
 * JDBC Implementation of CollegeModel.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public class CollegeModel {

	private static Logger log = Logger.getLogger(CollegeModel.class);

	/**
	 * Gets the next primary key from the database.
	 * 
	 * @return the next primary key
	 * @throws DatabaseException if any database error occurs
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Modal nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_college");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new DatabaseException("Exceptio :Exception in getting PK");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Adds a new College record.
	 * 
	 * @param bean the CollegeBean containing college details
	 * @return the primary key of the inserted record
	 * @throws ApplicationException     if any application-level error occurs
	 * @throws DuplicateRecordException if a duplicate college name already exists
	 */
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		CollegeBean duplicateCollegeName = findByName(bean.getName());

		if (duplicateCollegeName != null) {
			throw new DuplicateRecordException("College Name alredy exists");

		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_college values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception" + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in add college" + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Deletes a College record.
	 * 
	 * @param bean the CollegeBean containing the ID to be deleted
	 * @throws ApplicationException if any application-level error occurs
	 */
	public void delete(CollegeBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_college where Id=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			System.out.println("Deleting ID: " + bean.getId());
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception ", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception :Delete rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Modal delete End");
	}

	/**
	 * Finds a College by its name.
	 * 
	 * @param name the college name
	 * @return the CollegeBean if found, otherwise null
	 * @throws ApplicationException if any application-level error occurs
	 */
	public CollegeBean findByName(String name) throws ApplicationException {

		log.debug("Model findByName Started");

		StringBuffer sql = new StringBuffer("select * from st_college where name=?");
		CollegeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new ApplicationException("Exception : Exception in getting College byName");

		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("modal findByName End");
		return bean;
	}

	/**
	 * Finds a College by its primary key.
	 * 
	 * @param pk the primary key
	 * @return the CollegeBean if found, otherwise null
	 * @throws ApplicationException if any application-level error occurs
	 */
	public CollegeBean findByPK(long pk) throws ApplicationException {
		log.debug("Model Find BY Pk Stsrted");
		StringBuffer sql = new StringBuffer("select * from st_college where id=?");
		CollegeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception ", e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception is getting College byPK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Find By PK End");
		return bean;
	}

	/**
	 * Updates an existing College record.
	 * 
	 * @param bean the CollegeBean containing updated details
	 * @throws ApplicationException     if any application-level error occurs
	 * @throws DuplicateRecordException if a duplicate college name already exists
	 */
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		CollegeBean beanExist = findByName(bean.getName());

		// Check if updated College already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {

			throw new DuplicateRecordException("College is already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_college set name = ?, address = ?,state = ?, city =?, phone_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback exception " + ex.getMessage());
			}
			// throw new ApplicationException("Exception in updating College ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Searches for Colleges.
	 * 
	 * @param bean the CollegeBean containing search criteria
	 * @return list of matching CollegeBean objects
	 * @throws Exception if any error occurs
	 */
	public List search(CollegeBean bean) throws Exception {
		return search(bean, 0, 0);
	}

	/**
	 * Searches for Colleges with pagination.
	 * 
	 * @param bean     the CollegeBean containing search criteria
	 * @param pageNo   the current page number
	 * @param PageSize the number of records per page
	 * @return list of matching CollegeBean objects
	 * @throws Exception if any error occurs
	 */
	public List search(CollegeBean bean, int pageNo, int PageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_college where 1=1");
		System.out.println(bean.getId());
		if (bean != null) {
			System.out.println("collegeBean: " + bean.getId());
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				System.out.println("helooooooooo" + bean.getName());
				sql.append(" And name like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND address like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append("and state like'" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" and city like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" and phone_no = " + bean.getPhoneNo());
			}
		}

		// if page size is greater than zero then apply pagination
		if (PageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * PageSize;
			sql.append(" Limit " + pageNo + "," + PageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new CollegeBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAddress(rs.getString(3));
			bean.setState(rs.getString(4));
			bean.setCity(rs.getString(5));
			bean.setPhoneNo(rs.getString(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDatetime(rs.getTimestamp(9));
			bean.setModifiedDatetime(rs.getTimestamp(10));
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;
	}

	/**
	 * Returns the list of all Colleges.
	 * 
	 * @return list of CollegeBean objects
	 * @throws ApplicationException if any application-level error occurs
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Returns the list of Colleges with pagination.
	 * 
	 * @param pageNo   the current page number
	 * @param pageSize the number of records per page
	 * @return list of CollegeBean objects
	 * @throws ApplicationException if any application-level error occurs
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_college");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		CollegeBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
}
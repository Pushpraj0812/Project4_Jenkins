package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.UserBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Exception.RecordNotFoundException;
import com.rays.pro4.Util.EmailBuilder;
import com.rays.pro4.Util.EmailMessage;
import com.rays.pro4.Util.EmailUtility;
import com.rays.pro4.Util.JDBCDataSource;

/**
 * JDBC implementation of UserModel. Handles CRUD operations, authentication,
 * password management, and email notifications for users.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class UserModel {

	private static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Returns next primary key of User table.
	 *
	 * @return next primary key
	 * @throws DatabaseException if there is a database error
	 */

	public int nextPK() throws DatabaseException {

		log.debug("Model nextPK Started");

		String sql = "select max(id) from st_user";
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK Started");
		return pk + 1;

	}

	/**
	 * Adds a new user to the database.
	 *
	 * @param bean UserBean containing user data
	 * @return primary key of newly added user
	 * @throws ApplicationException     for database errors
	 * @throws DuplicateRecordException if login already exists
	 */
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		UserBean existbean = findByLogin(bean.getLogin());
		if (existbean != null) {
			throw new DuplicateRecordException("login Id already exists");

		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int a = pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception ...", e);
			try {
				e.printStackTrace();
				conn.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();
				throw new ApplicationException("Exception : add rollback exceptionn" + e2.getMessage());
			}
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Add End");
		return pk;

	}

	/**
	 * Deletes a user from the database.
	 *
	 * @param bean UserBean to delete
	 * @throws ApplicationException for database errors
	 */
	public void delete(UserBean bean) throws ApplicationException {
		log.debug("Model delete start");

		String sql = "delete from st_user where id=?";
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("DataBase Exception", e);
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception: Delete rollback Exception" + e2.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Delete End");
	}

	/**
	 * Finds a user by login.
	 *
	 * @param login user login
	 * @return UserBean if found, null otherwise
	 * @throws ApplicationException for database errors
	 */
	public UserBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLohin Started");
		String sql = "select * from st_user where login=?";
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exception .", e);
			throw new ApplicationException("Exception: Exception in getting user by Login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findby login end");
		return bean;
	}

	/**
	 * Finds a user by primary key.
	 *
	 * @param pk user id
	 * @return UserBean if found, null otherwise
	 * @throws ApplicationException for database errors
	 */
	public UserBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findBy PK start");

		String sql = "select * from st_user where id=?";
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exception ", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Method Find By PK end");
		return bean;
	}

	/**
	 * Updates a user.
	 *
	 * @param bean UserBean containing updated data
	 * @throws ApplicationException     for database errors
	 * @throws DuplicateRecordException if login already exists
	 */
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model Update Start");

		String sql = "update st_user set first_name=?, last_name=?, login=?, password=?, dob=?, mobile_no=?, role_id=?, gender=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?";
		Connection conn = null;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null && !(existBean.getId() == bean.getId())) {
			throw new DuplicateRecordException("LoginId is Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exception", e);
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new ApplicationException("Exception : Update Rollback Exception " + e2.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Update End ");
	}

	/**
	 * Searches users based on given criteria with pagination support.
	 *
	 * @param bean     UserBean containing search criteria
	 * @param pageNo   page number (for pagination)
	 * @param pageSize number of records per page
	 * @return list of UserBean objects matching the criteria
	 * @throws ApplicationException if a database error occurs
	 */
	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model Search Start");

		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");
		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}
			if (bean.getRoleId() > 0) {
				sql.append(" and role_id = " + bean.getRoleId());
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				Date d = new java.sql.Date(bean.getDob().getTime());
				sql.append(" and dob  Like = " + d + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no = " + bean.getMobileNo());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
		}
		List list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new ApplicationException("Exception: Exception in Search User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Search end");
		return list;

	}

	/**
	 * Authenticates a user by login and password.
	 *
	 * @param login    user's login (email)
	 * @param password user's password
	 * @return UserBean if authentication is successful, null otherwise
	 * @throws ApplicationException if a database error occurs
	 */
	public UserBean authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");

		StringBuffer sql = new StringBuffer("select * from st_user where login = ? and password = ?;");
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model authenticate End");
		return bean;

	}

	/**
	 * Changes the password of a user.
	 *
	 * @param id          user ID
	 * @param oldPassword current password
	 * @param newPassword new password to set
	 * @return true if password change was successful
	 * @throws ApplicationException    if a database error occurs
	 * @throws RecordNotFoundException if user does not exist or old password is
	 *                                 incorrect
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws ApplicationException, RecordNotFoundException {

		log.debug("Model chanfwPassword Started");

		boolean flag = false;
		UserBean beanexist = null;

		beanexist = findByPK(id);

		if (beanexist != null && beanexist.getPassword().equals(oldPassword)) {
			beanexist.setPassword(newPassword);

			try {
				update(beanexist);
			} catch (DuplicateRecordException e) {
				log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", beanexist.getLogin());
		map.put("password", beanexist.getPassword());
		map.put("firstName", beanexist.getFirstName());
		map.put("lastName", beanexist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(beanexist.getLogin());
		msg.setSubject("SUNRAYS ORS Password has been changed Successfuly.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		log.debug("Model changePassword End");
		return flag;
	}

	/**
	 * Registers a new user and sends a registration email.
	 *
	 * @param bean UserBean containing user details
	 * @return primary key of the newly registered user
	 * @throws ApplicationException     if a database error occurs
	 * @throws DuplicateRecordException if user already exists
	 */
	public long registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);
		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is Successful for ORS Project Sunilos");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}

	/**
	 * Sends the forgotten password to user's email.
	 *
	 * @param login user's login (email)
	 * @return true if email was sent successfully
	 * @throws ApplicationException    if a database error occurs
	 * @throws RecordNotFoundException if login does not exist
	 */
	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {

		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {

			throw new RecordNotFoundException("Email Id does not exist !");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());

		System.out.println("Login = " + userData.getLogin());
		System.out.println("Pwd = " + userData.getPassword());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("Sunrays ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		flag = true;
		return flag;
	}
}

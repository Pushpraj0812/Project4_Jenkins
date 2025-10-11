package com.rays.pro4.Bean;

import java.sql.Timestamp;

import java.util.Date;

/**
 * The {@code UserBean} class represents a user of the system. It encapsulates
 * user attributes such as personal details, login credentials, role, and
 * contact information.
 * 
 * This bean extends {@link BaseBean}, which provides common entity properties
 * like {@code id}, {@code createdBy}, {@code modifiedBy},
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to transfer user-related data between different
 * layers of an MVC-based application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class UserBean extends BaseBean {

	/** The user's first name. */
	private String firstName;

	/** The user's last name. */
	private String lastName;

	/** The login ID (typically email) used by the user. */
	private String login;

	/** The login password of the user. */
	private String password;

	/** The confirmation password entered by the user (not persisted). */
	private String confirmPassword;

	/** The date of birth of the user. */
	private Date dob;

	/** The mobile number of the user. */
	private String mobileNo;

	/** The role ID associated with the user. */
	private long roleId;

	/** The gender of the user. */
	private String gender;

	/** Gets the first name. */
	public String getFirstName() {
		return firstName;
	}

	/** Sets the first name. */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** Gets the last name. */
	public String getLastName() {
		return lastName;
	}

	/** Sets the last name. */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** Gets the login ID. */
	public String getLogin() {
		return login;
	}

	/** Sets the login ID. */
	public void setLogin(String login) {
		this.login = login;
	}

	/** Gets the password. */
	public String getPassword() {
		return password;
	}

	/** Sets the password. */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Gets the confirm password (not persisted). */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/** Sets the confirm password (not persisted). */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/** Gets the date of birth. */
	public Date getDob() {
		return dob;
	}

	/** Sets the date of birth. */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/** Gets the mobile number. */
	public String getMobileNo() {
		return mobileNo;
	}

	/** Sets the mobile number. */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/** Gets the role ID. */
	public long getRoleId() {
		return roleId;
	}

	/** Sets the role ID. */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/** Gets the gender. */
	public String getGender() {
		return gender;
	}

	/** Sets the gender. */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the primary key of this user as a {@code String}.
	 * 
	 * @return the user ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for this user, which is the full name (first name
	 * + last name).
	 * 
	 * @return the full name of the user
	 */
	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

	/**
	 * Returns a string representation of the user. Includes key details useful for
	 * debugging.
	 */

	@Override
	public String toString() {

		return "UserBean [password=" + password + ", dob=" + dob + "]";
	}
}
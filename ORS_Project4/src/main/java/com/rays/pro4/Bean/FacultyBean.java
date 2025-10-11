package com.rays.pro4.Bean;

import java.util.Date;

/**
 * The {@code FacultyBean} class represents a faculty entity in the system. It
 * stores personal details such as name, gender, email, and mobile number, as
 * well as associations with college, course, and subject information.
 * 
 * This bean extends {@link BaseBean}, which provides common entity properties
 * like {@code id}, {@code createdBy}, {@code modifiedBy},
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to transfer faculty-related data between
 * different layers of an MVC-based application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class FacultyBean extends BaseBean {

	/** The faculty's first name. */
	private String firstName;

	/** The faculty's last name. */
	private String lastName;

	/** The faculty's gender. */
	private String Gender;

	/** The faculty's email ID. */
	private String emailId;

	/** The faculty's mobile number. */
	private String mobileNo;

	/** The ID of the college associated with the faculty. */
	private long collegeId;

	/** The name of the college associated with the faculty. */
	private String collegeName;

	/** The ID of the course associated with the faculty. */
	private long courseId;

	/** The name of the course associated with the faculty. */
	private String courseName;

	/** The faculty's date of birth. */
	private Date dob;

	/** The ID of the subject associated with the faculty. */
	private long subjectId;

	/** The name of the subject associated with the faculty. */
	private String subjectName;

	/**
	 * Gets the faculty's first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the faculty's first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the faculty's last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the faculty's last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the faculty's gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return Gender;
	}

	/**
	 * Sets the faculty's gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		Gender = gender;
	}

	/**
	 * Gets the faculty's email ID.
	 *
	 * @return the email ID
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the faculty's email ID.
	 *
	 * @param emailId the new email ID
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the faculty's mobile number.
	 *
	 * @return the mobile number
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the faculty's mobile number.
	 *
	 * @param mobileNo the new mobile number
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the college ID associated with the faculty.
	 *
	 * @return the college ID
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college ID associated with the faculty.
	 *
	 * @param collegeId the new college ID
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the college name associated with the faculty.
	 *
	 * @return the college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the college name associated with the faculty.
	 *
	 * @param collegeName the new college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Gets the course ID associated with the faculty.
	 *
	 * @return the course ID
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with the faculty.
	 *
	 * @param courseId the new course ID
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name associated with the faculty.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name associated with the faculty.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the faculty's date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the faculty's date of birth.
	 *
	 * @param dob the new date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the subject ID associated with the faculty.
	 *
	 * @return the subject ID
	 */
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subject ID associated with the faculty.
	 *
	 * @param subjectId the new subject ID
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the subject name associated with the faculty.
	 *
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the subject name associated with the faculty.
	 *
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Returns the primary key of this faculty entity as a {@code String}.
	 * 
	 * @return the faculty ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for the faculty, which is the full name (first
	 * name + last name).
	 *
	 * @return the faculty full name
	 */
	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}
}
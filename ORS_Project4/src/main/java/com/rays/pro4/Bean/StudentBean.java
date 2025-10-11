package com.rays.pro4.Bean;

import java.util.Date;

import java.util.Date;

/**
 * The {@code StudentBean} class represents a student entity. 
 * It encapsulates student details such as name, date of birth, 
 * contact information, and college details. 
 * 
 * This bean extends {@link BaseBean}, which provides common entity 
 * properties like {@code id}, {@code createdBy}, {@code modifiedBy}, 
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to transfer student-related data 
 * between different layers of an MVC-based application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class StudentBean extends BaseBean {

    /** The student's first name. */
    private String firstName;

    /** The student's last name. */
    private String lastName;

    /** The student's date of birth. */
    private Date dob;

    /** The student's mobile number. */
    private String mobileNo;

    /** The student's email address. */
    private String email;

    /** The ID of the college associated with the student. */
    private long collegeId;

    /** The name of the college associated with the student. */
    private String collegeName;

    /**
     * Gets the student's first name.
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the student's first name.
     * 
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the student's last name.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the student's last name.
     * 
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the student's date of birth.
     * 
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the student's date of birth.
     * 
     * @param dob the new date of birth
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets the student's mobile number.
     * 
     * @return the mobile number
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the student's mobile number.
     * 
     * @param mobileNo the new mobile number
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * Gets the student's email address.
     * 
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email address.
     * 
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the college ID associated with the student.
     * 
     * @return the college ID
     */
    public long getCollegeId() {
        return collegeId;
    }

    /**
     * Sets the college ID associated with the student.
     * 
     * @param collegeId the new college ID
     */
    public void setCollegeId(long collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * Gets the college name associated with the student.
     * 
     * @return the college name
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Sets the college name associated with the student.
     * 
     * @param collegeName the new college name
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * Returns the primary key of this student entity as a {@code String}.
     * 
     * @return the student ID in string format
     */
    @Override
    public String getkey() {
        return id + "";
    }

    /**
     * Returns a displayable value for the student, 
     * which is the full name (first name + last name).
     * 
     * @return the student full name
     */
    @Override
    public String getValue() {
        return firstName + " " + lastName;
    }
}
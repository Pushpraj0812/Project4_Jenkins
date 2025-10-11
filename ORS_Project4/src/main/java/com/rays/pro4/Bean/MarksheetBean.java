package com.rays.pro4.Bean;

/**
 * The {@code MarksheetBean} class represents a marksheet entity. It
 * encapsulates details such as roll number, student information, and subject
 * marks (Physics, Chemistry, Mathematics).
 * 
 * This bean extends {@link BaseBean}, which provides common entity properties
 * like {@code id}, {@code createdBy}, {@code modifiedBy},
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to transfer marksheet-related data between
 * different layers of an MVC-based application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class MarksheetBean extends BaseBean {

	/** Roll number of the student. */
	private String rollNo;

	/** The ID of the student associated with this marksheet. */
	private long studentld;

	/** Name of the student. */
	private String name;

	/** Marks obtained in Physics. */
	private Integer physics;

	/** Marks obtained in Chemistry. */
	private Integer chemistry;

	/** Marks obtained in Mathematics. */
	private Integer maths;

	/**
	 * Gets the roll number of the student.
	 * 
	 * @return the roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets the roll number of the student.
	 * 
	 * @param rollNo the new roll number
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets the student ID.
	 * 
	 * @return the student ID
	 */
	public long getStudentld() {
		return studentld;
	}

	/**
	 * Sets the student ID.
	 * 
	 * @param studentld the new student ID
	 */
	public void setStudentld(long studentld) {
		this.studentld = studentld;
	}

	/**
	 * Gets the student's name.
	 * 
	 * @return the student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the student's name.
	 * 
	 * @param name the new student name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the marks obtained in Physics.
	 * 
	 * @return the physics marks
	 */
	public Integer getPhysics() {
		return physics;
	}

	/**
	 * Sets the marks obtained in Physics.
	 * 
	 * @param physics the new physics marks
	 */
	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	/**
	 * Gets the marks obtained in Chemistry.
	 * 
	 * @return the chemistry marks
	 */
	public Integer getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the marks obtained in Chemistry.
	 * 
	 * @param chemistry the new chemistry marks
	 */
	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Gets the marks obtained in Mathematics.
	 * 
	 * @return the maths marks
	 */
	public Integer getMaths() {
		return maths;
	}

	/**
	 * Sets the marks obtained in Mathematics.
	 * 
	 * @param maths the new maths marks
	 */
	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	/**
	 * Returns the primary key of this marksheet entity as a {@code String}.
	 * 
	 * @return the marksheet ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for the marksheet, which is the roll number.
	 * 
	 * @return the roll number
	 */
	@Override
	public String getValue() {
		return rollNo;
	}
}
package com.rays.pro4.Bean;

/**
 * The {@code SubjectBean} class represents a subject entity. It encapsulates
 * details of a subject such as its name, description, associated course ID, and
 * course name.
 * 
 * This bean extends {@link BaseBean}, which provides common entity properties
 * like {@code id}, {@code createdBy}, {@code modifiedBy},
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to transfer subject-related data between
 * different layers of an MVC-based application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class SubjectBean extends BaseBean {

	/** The name of the subject. */
	private String subjectName;

	/** The description of the subject. */
	private String description;

	/** The ID of the course associated with this subject. */
	private long courseId;

	/** The name of the course associated with this subject. */
	private String courseName;

	/**
	 * Gets the subject name.
	 * 
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the subject name.
	 * 
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Gets the description of the subject.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the subject.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the course ID associated with this subject.
	 * 
	 * @return the course ID
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with this subject.
	 * 
	 * @param courseId the new course ID
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name associated with this subject.
	 * 
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name associated with this subject.
	 * 
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Returns the primary key of this subject entity as a {@code String}.
	 * 
	 * @return the subject ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for the subject, which is the subject name.
	 * 
	 * @return the subject name
	 */
	@Override
	public String getValue() {
		return subjectName;
	}
}
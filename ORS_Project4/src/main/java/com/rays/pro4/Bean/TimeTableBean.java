package com.rays.pro4.Bean;

import java.util.Date;

/**
 * The {@code TimeTableBean} class represents a timetable entry for exams. It
 * encapsulates attributes such as course details, subject details, semester,
 * exam date, exam time, and an optional description.
 * 
 * This bean extends {@link BaseBean}, which provides common entity properties
 * like {@code id}, {@code createdBy}, {@code modifiedBy},
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to transfer timetable-related data between
 * different layers of an MVC-based application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class TimeTableBean extends BaseBean {

	/** The ID of the course for this timetable entry. */
	private long courseId;

	/** The name of the course for this timetable entry. */
	private String courseName;

	/** The ID of the subject for this timetable entry. */
	private long subjectId;

	/** The name of the subject for this timetable entry. */
	private String subjectName;

	/** The semester in which the exam is scheduled. */
	private String semester;

	/** The date of the exam. */
	private Date examDate;

	/** The time of the exam. */
	private String examTime;

	/** The description of the exam (optional). */
	private String description;

	/** Gets the course ID. */
	public long getCourseId() {
		return courseId;
	}

	/** Sets the course ID. */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/** Gets the course name. */
	public String getCourseName() {
		return courseName;
	}

	/** Sets the course name. */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/** Gets the subject ID. */
	public long getSubjectId() {
		return subjectId;
	}

	/** Sets the subject ID. */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/** Gets the subject name. */
	public String getSubjectName() {
		return subjectName;
	}

	/** Sets the subject name. */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/** Gets the semester. */
	public String getSemester() {
		return semester;
	}

	/** Sets the semester. */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/** Gets the exam date. */
	public Date getExamDate() {
		return examDate;
	}

	/** Sets the exam date. */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/** Gets the exam time. */
	public String getExamTime() {
		return examTime;
	}

	/** Sets the exam time. */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	/** Gets the description. */
	public String getDescription() {
		return description;
	}

	/** Sets the description. */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the primary key of this timetable entry as a {@code String}.
	 * 
	 * @return the timetable ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for this timetable entry, which is the subject
	 * name.
	 * 
	 * @return the subject name
	 */
	@Override
	public String getValue() {
		return subjectName;
	}
}
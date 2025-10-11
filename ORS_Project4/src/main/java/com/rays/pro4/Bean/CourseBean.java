package com.rays.pro4.Bean;

/**
 * The {@code CollegeBean} class represents a college entity and contains
 * details such as name, address, state, city, and phone number.
 * <p>
 * It extends {@code BaseBean} to inherit common properties like id, createdBy,
 * modifiedBy, etc.
 * <p>
 * This class also overrides {@code getKey()} and {@code getValue()} methods
 * from the {@code DropdownListBean} interface.
 *
 * @author Pushpraj Singh Kachhaway
 *
 */
public class CourseBean extends BaseBean {

	/** The name of the course. */
	private String name;

	/** A brief description of the course. */
	private String description;

	/** Duration of the course (e.g., "3 Months", "1 Year"). */
	private String duration;

	/**
	 * Gets the name of the course.
	 *
	 * @return the course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course.
	 *
	 * @param name the new course name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the course.
	 *
	 * @return the course description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the course.
	 *
	 * @param description the new course description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the duration of the course.
	 *
	 * @return the course duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the course.
	 *
	 * @param duration the new course duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Returns the primary key of this course as a {@code String}.
	 * 
	 * @return the course ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for the course, which is its name.
	 *
	 * @return the course name
	 */
	@Override
	public String getValue() {
		return name;
	}
}
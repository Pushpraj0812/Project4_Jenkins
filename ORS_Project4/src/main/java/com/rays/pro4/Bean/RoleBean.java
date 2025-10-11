package com.rays.pro4.Bean;

/**
 * The {@code RoleBean} class represents a role entity in the system. It defines
 * different user roles such as Admin, Student, College, Faculty, and Kiosk.
 * Each role has a unique ID, name, and description.
 * 
 * This bean extends {@link BaseBean}, which provides common entity properties
 * like {@code id}, {@code createdBy}, {@code modifiedBy},
 * {@code createdDatetime}, and {@code modifiedDatetime}.
 * 
 * Typically, this bean is used to manage user roles in an MVC-based
 * application.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class RoleBean extends BaseBean {

	/** Constant representing the Admin role. */
	public static final int ADMIN = 1;

	/** Constant representing the Student role. */
	public static final int STUDENT = 2;

	/** Constant representing the College role. */
	public static final int COLLEGE = 3;

	/** Constant representing the Faculty role. */
	public static final int FACULTY = 4;

	/** Constant representing the Kiosk role. */
	public static final int KIOSK = 5;

	/** The name of the role. */
	private String name;

	/** The description of the role. */
	private String description;

	/**
	 * Gets the name of the role.
	 * 
	 * @return the role name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 * 
	 * @param name the new role name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the role.
	 * 
	 * @return the role description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the role.
	 * 
	 * @param description the new role description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the primary key of this role entity as a {@code String}.
	 * 
	 * @return the role ID in string format
	 */
	@Override
	public String getkey() {
		return id + "";
	}

	/**
	 * Returns a displayable value for the role, which is the role name.
	 * 
	 * @return the role name
	 */
	@Override
	public String getValue() {
		return name;
	}
}
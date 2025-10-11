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
public class CollegeBean extends BaseBean {

	private String name;
	private String address;
	private String state;
	private String city;
	private String phoneNo;

	/**
	 * Gets the name of the college.
	 *
	 * @return the name of the college
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the college.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the address of the college.
	 *
	 * @return the address of the college
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the college.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the state where the college is located.
	 *
	 * @return the state of the college
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state where the college is located.
	 *
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the city where the college is located.
	 *
	 * @return the city of the college
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city where the college is located.
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the phone number of the college.
	 *
	 * @return the phone number of the college
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the college.
	 *
	 * @param phoneNo the phone number to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Returns the unique key for the dropdown list. This is typically used in UI
	 * components.
	 *
	 * @return the string representation of the ID
	 */
	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	/**
	 * Returns the value to be displayed in the dropdown list. This typically
	 * represents the name of the entity.
	 *
	 * @return the name of the college
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}

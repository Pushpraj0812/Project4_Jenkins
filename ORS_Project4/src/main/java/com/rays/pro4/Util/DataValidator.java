package com.rays.pro4.Util;

import java.util.Date;

/**
 * This class provides utility methods for validating input data. It includes
 * checks for null, integer, long, email, date, name, password, phone number,
 * roll number, and age.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class DataValidator {

	/**
	 * Checks if a string is null or empty after trimming.
	 * 
	 * @param val the input string
	 * @return true if null or empty, false otherwise
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is not null or empty.
	 * 
	 * @param val the input string
	 * @return true if not null and not empty, false otherwise
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);

	}

	/**
	 * Checks if a string can be parsed as an integer.
	 * 
	 * @param val the input string
	 * @return true if it is a valid integer, false otherwise
	 */
	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if a string can be parsed as a long.
	 * 
	 * @param val the input string
	 * @return true if it is a valid long, false otherwise
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is a valid email.
	 * 
	 * @param val the input string
	 * @return true if it matches email pattern, false otherwise
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is a valid date in the application format.
	 * 
	 * @param val the input string
	 * @return true if it can be parsed as date, false otherwise
	 */
	public static boolean isDate(String val) {
		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	/**
	 * Checks if a string is a valid name.
	 * 
	 * @param val the input string
	 * @return true if it matches the name pattern, false otherwise
	 */
	public static boolean isName(String val) {

		String namereg = "^[^-\\s][\\p{L} .'-]+$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is a valid password.
	 * 
	 * @param val the input string
	 * @return true if it matches the password pattern, false otherwise
	 */
	public static boolean isPassword(String val) {

		String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if the length of password is valid (8 to 12 characters).
	 * 
	 * @param val the input string
	 * @return true if length is between 8 and 12, false otherwise
	 */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is a valid Indian mobile number.
	 * 
	 * @param val the input string
	 * @return true if it matches mobile number pattern, false otherwise
	 */
	public static boolean isPhoneNo(String val) {

		String phonereg = "^[6-9][0-9]{9}$";

		if (isNotNull(val)) {
			try {
				return val.matches(phonereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if the mobile number has exactly 10 digits.
	 * 
	 * @param val the input string
	 * @return true if length is 10, false otherwise
	 */
	public static boolean isPhoneLength(String val) {

		if (isNotNull(val) && val.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is a valid roll number.
	 * 
	 * @param val the input string
	 * @return true if it matches pattern (two letters followed by three digits),
	 *         false otherwise
	 */
	public static boolean isRollNo(String val) {

		String rollreg = "[a-zA-Z]{2}[0-9]{3}";

		if (isNotNull(val)) {
			try {
				return val.matches(rollreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if age is greater than 18.
	 * 
	 * @param val the date of birth string
	 * @return true if age > 18, false otherwise
	 */
	public static boolean isAge(String val) {

		Date today = new Date();
		Date enterDate = DataUtility.getDate(val);

		int age = today.getYear() - enterDate.getYear();

		if (age > 18 && isNotNull(val)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a string is a valid mobile number.
	 * 
	 * @param val the input string
	 * @return true if matches mobile number pattern, false otherwise
	 */
	public static boolean isMobileNo(String val) {

		String mobreg = "^[6-9][0-9]{9}$";

		if (isNotNull(val) && val.matches(mobreg)) {

			return true;
		} else {
			return false;
		}
	}
}
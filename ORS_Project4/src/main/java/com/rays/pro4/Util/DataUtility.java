package com.rays.pro4.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DataUtility class provides methods to convert and format data from one type
 * to another. It includes methods for String, Integer, Long, Date, and
 * Timestamp conversions.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class DataUtility {

	/**
	 * Application Date Format
	 */
	public static final String APP_DATE_FORMAT = "dd/MM/yyyy";

	/**
	 * Application timestamp format (dd/MM/yyyy HH:mm:ss)
	 */
	public static final String APP_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Date formatter
	 */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);

	/**
	 * Date formatter for application timestamp format
	 */
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

	/**
	 * Trims the leading and trailing spaces of a String.
	 * 
	 * @param val the input String
	 * @return trimmed String if not null, otherwise returns original value
	 */
	public static String getString(String val) { // Trims and trailing and leading spaces of a String

		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	/**
	 * Converts an Object to String.
	 * 
	 * @param val the input Object
	 * @return String representation of the object or empty String if null
	 */
	public static String getStringData(Object val) { // Converts and Object to String

		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	/**
	 * Converts a String to integer.
	 * 
	 * @param val the input String
	 * @return integer value if valid, otherwise 0
	 */
	public static int getInt(String val) { // Converts String into Integer

		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	/**
	 * Converts a String to long.
	 * 
	 * @param val the input String
	 * @return long value if valid, otherwise 0
	 */
	public static long getLong(String val) { // Converts String into Long

		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	/**
	 * Converts a String to Date using application date format.
	 * 
	 * @param val the input String
	 * @return Date object or null if parsing fails
	 */
	public static Date getDate(String val) { // Converts String into Date

		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}

	/**
	 * Converts a Date object to String using application date format.
	 * 
	 * @param date the input Date
	 * @return formatted String or empty String if formatting fails
	 */
	public static String getDateString(Date date) { // Converts Date into String

		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * Returns a new Date object by adding a specified number of days to the given
	 * date.
	 *
	 * @param date the base date
	 * @param day  the number of days to add (can be negative to subtract days)
	 * @return new Date object with the days added, or null if input date is null
	 */
	public static Date getDate(Date date, int day) { // Gets date after n number of days

		return null;
	}

	/**
	 * Returns a Timestamp from a String using application timestamp format.
	 * 
	 * @param val input String
	 * @return Timestamp or null if parsing fails
	 */
	public static Timestamp getTimestamp(String val) { // Converts String into Time

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	/**
	 * Converts a long value to Timestamp.
	 * 
	 * @param l input long
	 * @return Timestamp or null if conversion fails
	 */
	public static Timestamp getTimestamp(long l) { // Converts a long into Timestamp

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	/**
	 * Returns the current Timestamp (current date and time).
	 * 
	 * @return current Timestamp
	 */
	public static Timestamp getCurrentTimestamp() { // Returns current timestamp (date + time to the second)
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	/**
	 * Returns milliseconds from a Timestamp object.
	 * 
	 * @param tm input Timestamp
	 * @return milliseconds as long or 0 if Timestamp is null
	 */
	public static long getTimestamp(Timestamp tm) { // Extracts milliseconds from a Timestamp
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
}
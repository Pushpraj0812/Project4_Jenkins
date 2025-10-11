package com.rays.pro4.Util;

import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.rays.pro4.Bean.DropdownListBean;
import com.rays.pro4.Model.UserModel;

/**
 * HTMLUtility is a helper class to produce HTML elements dynamically, such as
 * dropdown lists, from Maps or Lists.
 * 
 * Provides methods to generate HTML select elements with options.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class HTMLUtility {

	/**
	 * Generates an HTML select element from a HashMap.
	 * 
	 * @param name        Name attribute of the select element
	 * @param selectedVal Value to be selected by default
	 * @param map         HashMap containing option key-value pairs
	 * @return HTML string for the select element
	 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 169px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Generates an HTML select element from a List of DropdownListBean objects.
	 * 
	 * @param name        Name attribute of the select element
	 * @param selectedVal Value to be selected by default
	 * @param list        List of DropdownListBean containing options
	 * @return HTML string for the select element
	 */
	public static String getList(String name, String selectedVal, List list) {

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select style=\"width: 169px;text-align-last: center;\"; "
				+ "class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {
			key = obj.getkey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Test method to generate a dropdown HTML using a HashMap.
	 */
	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<>();
		map.put("male", "male");
		map.put("female", "female");

		String selectedValue = "male";
		String htmlSelectFromMap = HTMLUtility.getList("gender", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}

	/**
	 * Test method to generate a dropdown HTML using a List of DropdownListBean.
	 * 
	 * @throws Exception
	 */
	public static void testGetListByList() throws Exception {

		UserModel model = new UserModel();

		List<DropdownListBean> list = null;

		String selectedValue = null;

		String htmlSelectFromList = HTMLUtility.getList("name", selectedValue, list);

		System.out.println(htmlSelectFromList);
	}
}
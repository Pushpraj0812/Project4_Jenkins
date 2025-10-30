package com.rays.pro4.Bean;

import java.util.Date;

public class DoctorBean extends BaseBean {

	private String Name;
	private String Mobile;
	private String Experties;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getExperties() {
		return Experties;
	}

	public void setExperties(String experties) {
		Experties = experties;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return  Experties;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return Experties;
	}

}
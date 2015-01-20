package com.embark.phonebook.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Contacts {
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private HashMap<PhoneCategories, ArrayList<String>> allphno;

	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashMap<PhoneCategories, ArrayList<String>> getAllphno() {
		return allphno;
	}
	
	public void setAllphno(HashMap<PhoneCategories, ArrayList<String>> allphno) {
		this.allphno = allphno;
	}

}
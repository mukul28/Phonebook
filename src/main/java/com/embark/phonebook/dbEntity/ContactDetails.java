package com.embark.phonebook.dbEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactDetails {
	
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private List<PhoneNumber> phNos=new ArrayList<PhoneNumber>();

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PhoneNumber> getPhNos() {
		return phNos;
	}

	public void setPhNos(List<PhoneNumber> phNos) {
		this.phNos = phNos;
	}
	
}

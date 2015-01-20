package com.embark.phonebook.dbEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneNumber {
	private Integer pid;
	//private ArrayList<String> phnos = new ArrayList<String>();
	private String nos;
	private String type;
	private ContactDetails contact;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*public void setPhnos(ArrayList<String> ph) {
		this.phnos = ph;
	}

	public ArrayList<String> getPhnos() {
		return phnos;
	}*/

	public Integer getPid() {
		return pid;
	}

	public String getNos() {
		return nos;
	}

	public void setNos(String nos) {
		this.nos = nos;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public ContactDetails getContact() {
		return contact;
	}

	public void setContact(ContactDetails contact) {
		this.contact = contact;
	}
	

}

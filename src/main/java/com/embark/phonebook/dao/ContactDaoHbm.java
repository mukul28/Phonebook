package com.embark.phonebook.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.embark.phonebook.dbEntity.ContactDetails;
import com.embark.phonebook.dbEntity.PhoneNumber;
import com.embark.phonebook.model.Contacts;

public interface ContactDaoHbm {
	public void saveContact(Contacts Cd)//, PhoneNumber pn)
			throws SQLException;
	public ArrayList<Contacts> searchContact(String searchname);

}

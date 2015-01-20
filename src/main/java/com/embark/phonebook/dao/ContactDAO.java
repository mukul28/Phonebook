package com.embark.phonebook.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.embark.phonebook.dbEntity.ContactDetails;
import com.embark.phonebook.model.Contacts;


public interface ContactDAO
{
	public void saveContact(Contacts Con) throws SQLException;
	public ArrayList<Contacts> searchContact(String searchname);
}

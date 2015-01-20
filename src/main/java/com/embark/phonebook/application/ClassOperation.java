package com.embark.phonebook.application;

import java.io.IOException;

import com.embark.phonebook.utils.HibernateUtil;
import com.embark.phonebook.view.DirGUI;

public class ClassOperation {

	public static void main(String[] args) throws IOException {
		/*
		 * CreateFileio cre = new CreateFileio(); cre.create();
		 */
		DirGUI dir = new DirGUI();
		HibernateUtil.getSession();
/*		// dir.initComponents();
		
		 * Stbring searchname,yn,str12 = null; BufferedReader br = new
		 * BufferedReader(new InputStreamReader(System.in));
		 * 
		 * 
		 * Contacts c = new Contacts(); ContactDAOFileImpl f = new
		 * ContactDAOFileImpl();
		 * System.out.println("Do you want to add new contacts (y/n) :"); yn =
		 * br.readLine();
		 * 
		 * if(yn.equals("y")) { f.saveContact( c ); }
		 * //System.out.println(c.getAllphno());
		 * 
		 * System.out.println("Do you want to Search contacts in database (y/n) :"
		 * ); yn = br.readLine(); List am = new ArrayList();
		 * 
		 * if(yn.equals("y")) { System.out.println("Enter Surname to Search");
		 * searchname = br.readLine(); am = f.searchContact(searchname);
		 * System.out.println("Searched contacts are : "+am); }
		 
*/	}

}

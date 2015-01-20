package com.embark.phonebook.daoImpl;

import com.embark.phonebook.dao.ContactDAO;
import com.embark.phonebook.dbEntity.ContactDetails;
import com.embark.phonebook.model.Contacts;
import com.embark.phonebook.model.PhoneCategories;
import com.embark.phonebook.utils.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ContactDAODBImpl implements ContactDAO {

	Connection c = null;
	Statement s;
	private String Fname, Lname, Emailid;
	private ArrayList<Contacts> retcon;
	HashMap<PhoneCategories, ArrayList<String>> phoneno;
	private HashMap<PhoneCategories, ArrayList<String>> serchPhone;

	public ContactDAODBImpl() {
		/*
		 * try {
		 * 
		 * Class.forName("com.mysql.jdbc.Driver"); c =
		 * DriverManager.getConnection(
		 * "jdbc:mysql://localhost:3306/phonebookdb", "root", "root"); s =
		 * c.createStatement();
		 * 
		 * 
		 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); c =
		 * DriverManager.getConnection("jdbc:odbc:phonebook", "sa", "Sa@123"); s
		 * = c.createStatement();
		 * 
		 * } catch (SQLException se) { se.printStackTrace(); } catch
		 * (ClassNotFoundException ce) { ce.printStackTrace(); }
		 */}

	public void saveContact(Contacts Con) throws SQLException {
		Fname = Con.getFname();
		Lname = Con.getLname();
		Emailid = Con.getEmail();
		phoneno = Con.getAllphno();
		int id = 0;

		Session sn = HibernateUtil.getSession();
		Transaction t = sn.beginTransaction();

		ArrayList<String> officeNos = phoneno.get(PhoneCategories.OFFICE);
		ArrayList<String> home = phoneno.get(PhoneCategories.HOME);
		ArrayList<String> mobile = phoneno.get(PhoneCategories.MOBILE);
		System.out.println(home);
		sn.save(Con);
		t.commit();

		/*
		 * String stat = "insert into contacts(Fname,Lname,Email) values('" +
		 * Fname + "','" + Lname + "','" + Emailid + "')";
		 * s.executeUpdate(stat); String last_id =
		 * "select max(id) from contacts"; ResultSet rs =
		 * s.executeQuery(last_id);
		 * 
		 * while (rs.next()) { id = rs.getInt(1); }
		 * 
		 * savePhoneNo(home, id, "HOME"); savePhoneNo(mobile, id, "MOBILE");
		 * savePhoneNo(officeNos, id, "OFFICE"); s.close(); c.close();
		 */
	}

	public void savePhoneNo(ArrayList<String> phNo, int id, String typ) {
		Iterator<String> it = phNo.iterator();
		try {
			while (it.hasNext()) {
				String st = "insert into Phone(ContactId,PhType,Number) values("
						+ id + ",'" + typ + "','" + it.next() + "')";
				s.executeUpdate(st);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public ArrayList<Contacts> searchContact(String searchname) {
		try {
			Contacts c = new Contacts();
			Session sn1 = HibernateUtil.getSession();
			Transaction t1 = sn1.beginTransaction();

			serchPhone = new HashMap<PhoneCategories, ArrayList<String>>();
			ArrayList<String> HomePh = new ArrayList<String>();
			ArrayList<String> MobilePh = new ArrayList<String>();
			ArrayList<String> OfficePh = new ArrayList<String>();
			HashMap<Integer, Contacts> temp = new HashMap<Integer, Contacts>();
			String stat = "SELECT c.id,c.Fname,c.Lname,c.Email,p.PhType,p.Number FROM contacts c inner join phone p on c.id=p.ContactId where c.Lname like '"
					+ searchname + "%" + "' ";
			ResultSet rs = s.executeQuery(stat);
			retcon = new ArrayList<Contacts>();
			while (rs.next()) {
				if (temp.containsKey(rs.getInt("id"))) {

					c.setFname(rs.getString("Fname"));
					c.setLname(rs.getString("Lname"));
					c.setEmail(rs.getString("Email"));
					if (rs.getString("PhType").equals("HOME")) {
						HomePh.add(rs.getString("Number"));
					}
					serchPhone.put(PhoneCategories.HOME, HomePh);
					if (rs.getString("PhType").equals("MOBILE")) {
						MobilePh.add(rs.getString("Number"));
					}
					serchPhone.put(PhoneCategories.MOBILE, MobilePh);
					if (rs.getString("PhType").equals("OFFICE")) {
						OfficePh.add(rs.getString("Number"));
					}
					serchPhone.put(PhoneCategories.OFFICE, OfficePh);
					c.setAllphno(serchPhone);
					temp.put(rs.getInt("id"), c);

				} else {
					c = new Contacts();
					retcon.add(c);
					temp.put(rs.getInt("id"), c);
					serchPhone = new HashMap<PhoneCategories, ArrayList<String>>();
					HomePh = new ArrayList<String>();
					MobilePh = new ArrayList<String>();
					OfficePh = new ArrayList<String>();
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return retcon;
	}
}

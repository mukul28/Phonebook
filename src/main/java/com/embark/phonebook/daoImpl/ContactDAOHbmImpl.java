package com.embark.phonebook.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.embark.phonebook.dao.ContactDaoHbm;
import com.embark.phonebook.dbEntity.ContactDetails;
import com.embark.phonebook.dbEntity.PhoneNumber;
import com.embark.phonebook.model.Contacts;
import com.embark.phonebook.model.PhoneCategories;
import com.embark.phonebook.utils.HibernateUtil;

public class ContactDAOHbmImpl implements ContactDaoHbm {
	String str;
	
	private HashMap<PhoneCategories, ArrayList<String>> phoneno, serchPhone;
	public static ArrayList<Contacts> ret = new ArrayList<Contacts>();
	
	public void saveContact(Contacts Cd) throws SQLException {
		phoneno = Cd.getAllphno();
		PhoneNumber pn;
		ContactDetails c = new ContactDetails();

		Session sn = HibernateUtil.getSession();
		Transaction t = sn.beginTransaction();
		c.setEmail(Cd.getEmail());
		c.setFname(Cd.getFname());
		c.setLname(Cd.getLname());
		for (PhoneCategories p : Cd.getAllphno().keySet()) {
			for (String number : Cd.getAllphno().get(p)) {
				pn = new PhoneNumber();
				pn.setNos(number);
				pn.setType(p.toString());
				c.getPhNos().add(pn);
			}
		}
		sn.save(c);
		t.commit();
	}

	@Override
	public ArrayList<Contacts> searchContact(String searchname) {
		Session session = HibernateUtil.openSession();
		Transaction ts = null;
		ArrayList<Contacts> condet = new ArrayList<Contacts>();
		Contacts c;
		
		List<PhoneNumber> phn = new ArrayList<PhoneNumber>();
		PhoneNumber p = new PhoneNumber();

		HashMap<PhoneCategories, ArrayList<String>> searchPhone;
		searchPhone = new HashMap<PhoneCategories, ArrayList<String>>();
		ArrayList<String> HomePh = new ArrayList<String>();
		ArrayList<String> MobilePh = new ArrayList<String>();
		ArrayList<String> OfficePh = new ArrayList<String>();
		try {
			/*
			 * ContactDetails cd = (ContactDetails) session.get(
			 * ContactDetails.class, searchname);
			 */
			ContactDetails con = new ContactDetails();
			c = new Contacts();
			System.out.println(searchname);
			condet = (ArrayList) session.createQuery("FROM ContactDetails where lname LIKE '"+searchname +"%" +"'")
					.list();
			System.out.println(condet);
			for (Iterator iterator1 = condet.iterator(); iterator1.hasNext();) {
				 con= (ContactDetails) iterator1.next();
				c.setFname(con.getFname());
				c.setLname(con.getLname());
				c.setEmail(con.getEmail());
				//ret.add(c);
			}
			System.out.println(con.getId());

			phn = (ArrayList) session.createQuery("FROM PhoneNumber where ContactId = '"+con.getId()+"'").list();

			for (Iterator iterator2 = phn.iterator(); iterator2.hasNext();) {
				PhoneNumber ph = (PhoneNumber) iterator2.next();
				// System.out.println(ph.getType() + "sc  " + ph.getNos());

				if (ph.getType().equals("HOME")) {
					HomePh.add(ph.getNos());
					searchPhone.put(PhoneCategories.HOME, HomePh);
				}
				

				if (ph.getType().equals("MOBILE")) {
					MobilePh.add(ph.getNos());
					searchPhone.put(PhoneCategories.MOBILE, MobilePh);
				}
				

				if (ph.getType().equals("OFFICE")) {
					OfficePh.add(ph.getNos());
					searchPhone.put(PhoneCategories.OFFICE, OfficePh);
				}
				
				c.setAllphno(searchPhone);
			}
			
			
			//System.out.println(c);
			ret.add(c);

			//ts.commit();
			return ret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}

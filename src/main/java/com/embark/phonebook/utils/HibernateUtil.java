package com.embark.phonebook.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionf;

	static {
		sessionf = new Configuration().configure().buildSessionFactory();

	}

	public static Session getSession() {

		/*
		 * if(sessionf==null) sessionf = new
		 * Configuration().configure().buildSessionFactory();
		 */
		Session session = sessionf.getCurrentSession();
		return session;
	}

	public static Session openSession() {
		Session session = sessionf.openSession();
		return session;
	}

	public static void closeSession(Session ssn) {
		ssn.close();
	}
}

package com.embark.phonebook.daoImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.embark.phonebook.dao.ContactDAO;
import com.embark.phonebook.model.Contacts;
import com.embark.phonebook.model.PhoneCategories;

public class ContactDAOFileImpl implements ContactDAO {
	private BufferedReader br3;
	private FileWriter falpha, fnumber, fschar;
	private String name, fname, lname, email;

	private int i1, i2, i3;
	private ArrayList a1, a2, a3, alph, arraylist;
	private HashMap phoneMap;
	private String yes;
	private int fl = 0;
	private String sname = null, str;
	private StringTokenizer token, token1, token2;
	private String tsrno1, tname1, tphno1, teid1;
	private String tsrno = null, tname = null, tphno = null, teid = null,
			st = null, tfnm = null, tlnm = null;
	private File f1, fc;

	public void saveContact(Contacts con) {

		try {

			StringBuffer phoneNumbers = new StringBuffer();

			fname = con.getFname();
			lname = con.getLname();

			email = con.getEmail();

			HashMap<PhoneCategories, ArrayList<String>> phnos = con
					.getAllphno();

			ArrayList<String> oficeNos = phnos.get(PhoneCategories.OFFICE);
			ArrayList<String> home = phnos.get(PhoneCategories.HOME);
			ArrayList<String> mobile = phnos.get(PhoneCategories.MOBILE);

			phoneNumbers = phoneNumbers.append("HOME-");
			for (int x = 0; x < home.size(); x++) {
				phoneNumbers = phoneNumbers.append(home.get(x));
				if (x != (home.size() - 1))
					phoneNumbers = phoneNumbers.append(", ");
				else
					phoneNumbers = phoneNumbers.append("; ");

			}
			phoneNumbers = phoneNumbers.append("MOBILE-");
			for (int x = 0; x < mobile.size(); x++) {
				phoneNumbers = phoneNumbers.append(mobile.get(x));
				if (x != (mobile.size() - 1))
					phoneNumbers = phoneNumbers.append(", ");
				else
					phoneNumbers = phoneNumbers.append("; ");

			}
			phoneNumbers = phoneNumbers.append("OFFICE-");
			for (int x = 0; x < oficeNos.size(); x++) {
				phoneNumbers = phoneNumbers.append(oficeNos.get(x));
				if (x != (oficeNos.size() - 1))
					phoneNumbers = phoneNumbers.append(", ");
				else
					phoneNumbers = phoneNumbers.append("; ");

			}

			name = lname + " " + fname;

			String searchalpha = (lname.substring(0, 1)).toUpperCase();

			if ((int) searchalpha.charAt(0) >= 48
					&& (int) searchalpha.charAt(0) <= 57) {
				File f1 = new File(
						"D:\\Setups\\java\\PhoneBookAssNew\\Numbers.txt");
				fnumber = new FileWriter(f1, true);

				String s1, sr = null;
				int ss = 0;
				/*
				 * br4 = new BufferedReader(new FileReader(f1)); while ((s1 =
				 * br4.readLine()) != null && !s1.isEmpty()) {
				 * System.out.println("ReadLine == " + s1); token2 = new
				 * StringTokenizer(s1, ":"); if (token2.countTokens() == 4) { sr
				 * = token2.nextToken(); } } ss = Integer.parseInt(sr);
				 */
				System.out.println("Sr no " + ss);
				fnumber.append((++ss) + ":" + name.toUpperCase() + ":"
						+ phoneNumbers.toString() + ":" + email);
				fnumber.append("\n");
				fnumber.flush();
				fnumber.close();
			} else if ((int) searchalpha.charAt(0) >= 65
					&& (int) searchalpha.charAt(0) <= 90) {

				File f2 = new File("D:\\Setups\\java\\PhoneBookAssNew\\"
						+ searchalpha.toUpperCase() + ".txt");
				falpha = new FileWriter(f2, true);

				String s1, sr = null;
				int ss = 0;
				/*
				 * br4 = new BufferedReader(new FileReader(f2));
				 * while((s1=br4.readLine())!=null && !s1.isEmpty()) {
				 * System.out.println("ReadLine == "+s1); token2 = new
				 * StringTokenizer(s1, ":"); if(token2.countTokens()==4) { sr =
				 * token2.nextToken(); } } ss = Integer.parseInt(sr);
				 * 
				 * System.out.println("Previous Sr no "+ss);
				 */

				falpha.append((++ss) + ":" + name.toUpperCase() + ":"
						+ phoneNumbers.toString() + ":" + email);
				falpha.append("\n");
				falpha.flush();
				falpha.close();
			} else {
				File f3 = new File(
						"D:\\Setups\\java\\PhoneBookAssNew\\SpecialCharacter.txt");
				fschar = new FileWriter(f3, true);

				String s1, sr = null;
				int ss = 0;
				/*
				 * br4 = new BufferedReader(new FileReader(f3)); while ((s1 =
				 * br4.readLine()) != null && !s1.isEmpty()) {
				 * System.out.println("ReadLine == " + s1); token2 = new
				 * StringTokenizer(s1, ":"); if (token2.countTokens() == 4) { sr
				 * = token2.nextToken(); } } ss = Integer.parseInt(sr);
				 */System.out.println("Previous Sr no " + ss);
				fschar.append((++ss) + ":" + name.toUpperCase() + ":"
						+ phoneNumbers.toString() + ":" + email);
				fschar.append("\n");
				fschar.flush();
				fschar.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Contacts> searchContact(String searchname) {
		Contacts c1 = new Contacts();
		int len;
		sname = searchname;
		len = searchname.length();
		ArrayList<Contacts> returnContacts = new ArrayList<Contacts>();

		List<Contacts> arrayreturn = new ArrayList<Contacts>();
		try {
			String fsearchnm = (sname.substring(0, 1)).toUpperCase();
			if ((int) fsearchnm.charAt(0) >= 65
					&& (int) fsearchnm.charAt(0) <= 90) {
				File fsearchalpha = new File(
						"D:\\Setups\\java\\PhoneBookAssNew\\" + fsearchnm
								+ ".txt");
				returnContacts = fileTokenize(fsearchalpha, c1);

			} else if ((int) fsearchnm.charAt(0) >= 48
					&& (int) fsearchnm.charAt(0) <= 57) {
				File fsearchnum = new File(
						"D:\\Setups\\java\\PhoneBookAssNew\\Numbers.txt");
				returnContacts = fileTokenize(fsearchnum, c1);
			} else {
				File fschar = new File(
						"D:\\Setups\\java\\PhoneBookAssNew\\SpecialCharacter.txt");
				returnContacts = fileTokenize(fschar, c1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnContacts;
	}

	public ArrayList<Contacts> fileTokenize(File f3, Contacts cs)
			throws IOException {

		br3 = new BufferedReader(new FileReader(f3));

		ArrayList<Contacts> returnContacts = new ArrayList<Contacts>();
		String search = "";
		while ((str = br3.readLine()) != null) {
			cs = new Contacts();
			token = new StringTokenizer(str, ":");
			if (token.countTokens() == 4) {
				tsrno = token.nextToken();
				tname = token.nextToken();
				tphno = token.nextToken();
				teid = token.nextToken();
			}

			token1 = new StringTokenizer(tname, " ");
			if (token1.countTokens() == 2) {
				tfnm = token1.nextToken();
				tlnm = token1.nextToken();
			}

			if (tname.startsWith(sname.toUpperCase())) {
				fl = 1;
				// cs.setId(tsrno);
				cs.setFname(tfnm);
				cs.setLname(tlnm);
				cs.setAllphno(parsePhno(tphno));
				cs.setEmail(teid);
				System.out.println("Name :" + cs.getLname() + " "
						+ cs.getFname() + " " + cs.getAllphno() + " "
						+ cs.getEmail());
				returnContacts.add(cs);
			} else {
				fl = 0;
			}

			// System.out.println("FFFFF" + returnContacts);

		}
		if (fl == 0) {
			System.out.println("Record not found");
		}

		return returnContacts;

	}

	private HashMap parsePhno(String tphno2) {
		HashMap<PhoneCategories, ArrayList<String>> returnMap = new HashMap<PhoneCategories, ArrayList<String>>();
		StringTokenizer token = new StringTokenizer(tphno2, ";");
		String homeph1 = "", mob1 = "", off1 = "";

		homeph1 = token.nextToken();

		mob1 = token.nextToken();
		off1 = token.nextToken();

		String home[] = new String[3];
		String mobile[] = new String[3];
		String office[] = new String[3];

		String phlist[] = new String[6];

		StringTokenizer token1 = new StringTokenizer(homeph1, "-");
		if (token1.countTokens() == 2) {
			home[0] = token1.nextToken();
			home[1] = token1.nextToken();
		} else {
			home[1] = "N/A ";
		}

		StringTokenizer token2 = new StringTokenizer(mob1, "-");
		if (token2.countTokens() == 2) {
			mobile[0] = token2.nextToken();
			mobile[1] = token2.nextToken();
		} else {
			mobile[1] = "N/A ";
		}

		StringTokenizer token3 = new StringTokenizer(off1, "-");
		if (token3.countTokens() == 2) {
			office[0] = token3.nextToken();
			office[1] = token3.nextToken();
		} else {
			office[1] = "N/A ";
		}

		ArrayList<String> homelist = new ArrayList<String>();
		homelist.add(home[1]);

		ArrayList<String> mobilelist = new ArrayList<String>();
		mobilelist.add(mobile[1]);

		ArrayList<String> officelist = new ArrayList<String>();
		officelist.add(office[1]);

		returnMap.put(PhoneCategories.HOME, homelist);
		returnMap.put(PhoneCategories.MOBILE, mobilelist);
		returnMap.put(PhoneCategories.OFFICE, officelist);

		return returnMap;

	}
}
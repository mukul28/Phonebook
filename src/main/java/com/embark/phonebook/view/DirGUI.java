package com.embark.phonebook.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.embark.phonebook.dao.ContactDaoHbm;
import com.embark.phonebook.daoImpl.ContactDAOHbmImpl;
import com.embark.phonebook.dbEntity.ContactDetails;
import com.embark.phonebook.dbEntity.PhoneNumber;
import com.embark.phonebook.model.Contacts;
import com.embark.phonebook.model.PhoneCategories;
import com.embark.phonebook.model.ValidationImpl;

public class DirGUI extends JFrame implements ChangeListener {

	private JTabbedPane jtp;

	public DirGUI() {

		jtp = new JTabbedPane();
		jtp.addTab("Add Contacts", new AddTab());
		jtp.addTab("Search Contacts", new SearchTab());

		setVisible(true);
		setContentPane(jtp);
		setBounds(100, 100, 850, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jtp.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		int i = jtp.getSelectedIndex();
		if (jtp.getSelectedIndex() == 0) {
			new SearchTab().clear();
			new SearchTab().repaint();
		} else if (jtp.getSelectedIndex() == 1) {
			new AddTab().clear();
			new AddTab().repaint();
		}
	}

}

class AddTab extends JDesktopPane implements ActionListener, FocusListener {
	private JTextField Name1, Surname1, homeno, mobileno, office, email;
	private JButton BtnOK, BtnCn, Btnaddhomeno, Btnaddmobileno, Btnaddofficeno;
	private JLabel Name, Surname, HomeNo, Mobile, Office, email1;
	private JLabel FnmErr, LnmErr, HnoErr, MnoErr, OnoErr, EidErr, AllErr;
	private JLabel HomeLbl, MobLbl, OffLbl;
	private String str_hno = "", str_mno = "", str_ono = "", valemail = "";
	ValidationImpl vmi = new ValidationImpl();
	static ArrayList<String> homenos = new ArrayList<String>();
	static ArrayList<String> mobileNos = new ArrayList<String>();
	static ArrayList<String> officeNos = new ArrayList<String>();

	public AddTab() {
		Name = new JLabel("Name: ");
		Surname = new JLabel("Surname: ");
		HomeNo = new JLabel("Home No.: ");
		Mobile = new JLabel("Mobile No.: ");
		Office = new JLabel("Oficce No.: ");
		email1 = new JLabel("email_id: ");
		FnmErr = new JLabel();
		LnmErr = new JLabel();
		HnoErr = new JLabel();
		MnoErr = new JLabel();
		OnoErr = new JLabel();
		HomeLbl = new JLabel();
		MobLbl = new JLabel();
		OffLbl = new JLabel();
		EidErr = new JLabel();
		AllErr = new JLabel();

		Name1 = new JTextField(10);
		Surname1 = new JTextField(10);
		homeno = new JTextField(7);
		mobileno = new JTextField(10);
		office = new JTextField(7);
		email = new JTextField(20);

		BtnOK = new JButton("OK");
		BtnCn = new JButton("Reset");
		Btnaddhomeno = new JButton();
		Btnaddmobileno = new JButton();
		Btnaddofficeno = new JButton();

		Name.setBounds(10, 10, 50, 50);
		add(Name);
		Name.setBackground(Color.RED);
		Name1.setBounds(90, 10, 100, 30);
		add(Name1);
		FnmErr.setForeground(Color.RED);
		FnmErr.setBounds(230, 10, 150, 30);
		add(FnmErr);

		Surname.setBounds(10, 50, 70, 30);
		add(Surname);
		Surname1.setBounds(90, 50, 100, 30);
		add(Surname1);
		LnmErr.setForeground(Color.RED);
		LnmErr.setBounds(230, 50, 150, 30);
		add(LnmErr);

		HomeNo.setBounds(10, 90, 70, 30);
		add(HomeNo);
		homeno.setBounds(90, 90, 100, 30);
		add(homeno);
		Btnaddhomeno.setBounds(200, 95, 20, 20);
		Btnaddhomeno.setToolTipText("Add Another No");
		add(Btnaddhomeno);
		HnoErr.setBounds(230, 90, 100, 30);
		HnoErr.setForeground(Color.RED);
		add(HnoErr);
		HomeLbl.setBounds(90, 115, 250, 30);
		add(HomeLbl);

		Mobile.setBounds(10, 140, 70, 30);
		add(Mobile);
		mobileno.setBounds(90, 140, 100, 30);
		add(mobileno);
		Btnaddmobileno.setBounds(200, 145, 20, 20);
		Btnaddmobileno.setToolTipText("Add Another No");
		add(Btnaddmobileno);
		MnoErr.setBounds(230, 140, 100, 30);
		MnoErr.setForeground(Color.RED);
		add(MnoErr);
		MobLbl.setBounds(90, 165, 270, 30);
		add(MobLbl);

		Office.setBounds(10, 190, 70, 30);
		add(Office);
		office.setBounds(90, 190, 100, 30);
		add(office);
		Btnaddofficeno.setBounds(200, 195, 20, 20);
		Btnaddofficeno.setToolTipText("Add Another No");
		add(Btnaddofficeno);
		OnoErr.setBounds(230, 190, 100, 20);
		OnoErr.setForeground(Color.RED);
		add(OnoErr);
		OffLbl.setBounds(90, 215, 250, 30);
		add(OffLbl);

		email1.setBounds(10, 240, 70, 30);
		add(email1);
		email.setBounds(90, 240, 100, 30);
		add(email);
		EidErr.setBounds(200, 240, 130, 30);
		EidErr.setForeground(Color.RED);
		add(EidErr);

		BtnOK.setBounds(10, 300, 100, 30);
		add(BtnOK);
		BtnOK.addActionListener(this);
		BtnCn.setBounds(120, 300, 100, 30);
		add(BtnCn);
		AllErr.setBounds(10, 330, 200, 30);
		AllErr.setForeground(Color.RED);
		add(AllErr);

		BtnCn.addActionListener(this);
		Btnaddhomeno.addActionListener(this);
		Btnaddmobileno.addActionListener(this);
		Btnaddofficeno.addActionListener(this);
		homeno.addFocusListener(this);
		mobileno.addFocusListener(this);
		office.addFocusListener(this);

		Name1.addFocusListener(this);
		Surname1.addFocusListener(this);

		Surname1.setEnabled(false);
		homeno.setEnabled(false);
		mobileno.setEnabled(false);
		office.setEnabled(false);
		email.setEnabled(false);
		BtnOK.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String fnm, lnm, emailid;
		Contacts c;
		ContactDetails cd;
		PhoneNumber pn;

		ContactDaoHbm dao = new ContactDAOHbmImpl();

		try {
			if (arg0.getSource() == BtnOK) {
				c = new Contacts();
				cd = new ContactDetails();
				pn = new PhoneNumber();

				fnm = Name1.getText().toUpperCase();
				c.setFname(fnm);

				lnm = Surname1.getText().toUpperCase();
				c.setLname(lnm);

				HashMap<PhoneCategories, ArrayList<String>> allPhonenos = new HashMap<PhoneCategories, ArrayList<String>>();

				if (HomeLbl.getText().isEmpty() && homeno.getText().isEmpty()) {
					homenos.add("N/A");
				} else {
					if (homeno.getText().length() == 7) {
						homenos.add(homeno.getText());
					}
				}
				allPhonenos.put(PhoneCategories.HOME, homenos);

				if (mobileno.getText().length() == 10) {
					mobileNos.add(mobileno.getText());
				}
				allPhonenos.put(PhoneCategories.MOBILE, mobileNos);

				if (OffLbl.getText().isEmpty() && OffLbl.getText().isEmpty()) {
					officeNos.add("N/A");
				} else {
					if (office.getText().length() == 7) {
						officeNos.add(office.getText());
					}
				}

				allPhonenos.put(PhoneCategories.OFFICE, officeNos);
				c.setAllphno(allPhonenos);

				emailid = email.getText();
				if (emailid.isEmpty()) {
					emailid = "N/A";
					c.setEmail(emailid);
					dao.saveContact(c);// , pn);
				} else {
					boolean flagEmId = vmi.validateIsAlpanumeric(emailid);
					if (flagEmId == true) {
						c.setEmail(emailid);
						dao.saveContact(c);// , pn);
					} else {
						EidErr.setText("Enter Invalid Email");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (arg0.getSource() == BtnCn) {
			clear();

		}

		if (arg0.getSource() == Btnaddhomeno) {
			if (homeno.getText().length() == 7) {
				if (vmi.flagNumeric == true) {
					homenos.add(homeno.getText());
					System.out.println("h" + homenos);
					str_hno += homeno.getText() + " ";
					HomeLbl.setText(str_hno);
					homeno.setText("");
					HnoErr.setText("");
				}
			}
		}

		if (arg0.getSource() == Btnaddmobileno) {
			if (mobileno.getText().length() == 10) {
				if (vmi.flagNumeric == true) {
					mobileNos.add(mobileno.getText());
					str_mno += mobileno.getText() + " ";
					MobLbl.setText(str_mno);
					mobileno.setText("");
					MnoErr.setText("");
				}
			}
		}
		if (arg0.getSource() == Btnaddofficeno) {
			if (office.getText().length() == 7) {
				if (vmi.flagNumeric == true) {
					officeNos.add(office.getText());
					str_ono += office.getText() + " ";
					OffLbl.setText(str_ono);
					office.setText("");
					OnoErr.setText("");
				}
			}
		}

	}

	public void clear() {
		Name1.setText("");
		Surname1.setText("");
		homeno.setText("");
		mobileno.setText("");
		office.setText("");
		email.setText("");
		HomeLbl.setText("");
		MobLbl.setText("");
		OffLbl.setText("");
		HnoErr.setText("");
		OnoErr.setText("");
		MnoErr.setText("");
		str_hno = "";
		str_ono = "";
		str_mno = "";

	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource() == Name1) {

			if (Name1.getText().isEmpty()) {
				FnmErr.setText("Name is Compulsury");
			} else {
				boolean len = vmi.validateLength(Name1.getText(), 1, 25);
				if (len) {
					boolean istrue = vmi.validateIsAlphabetic(Name1.getText()
							.toUpperCase());
					if (!istrue) {
						FnmErr.setText("Invalid Name");
						Name1.setText("");
					} else {
						FnmErr.setText("");
						Surname1.setEnabled(true);
					}
				}
			}

		}

		if (arg0.getSource() == Surname1) {
			if (Surname1.getText().isEmpty()) {
				LnmErr.setText("Surname is Compulsury");
			} else {
				boolean len = vmi.validateLength(Surname1.getText(), 1, 25);
				if (len) {
					boolean istrue = vmi.validateIsAlphabetic(Surname1
							.getText().toUpperCase());
					if (!istrue) {
						LnmErr.setText("Invalid Last Name");
						Surname1.setText("");
					} else {
						LnmErr.setText("");
						homeno.setEnabled(true);
						mobileno.setEnabled(true);
						office.setEnabled(true);
						email.setEnabled(true);
					}
				}
			}
		}

		if (arg0.getSource() == homeno) {
			if (homeno.getText().isEmpty()) {

				HnoErr.setText("");
			} else {
				if (homeno.getText().length() == 7) {
					boolean istrue = vmi.validateIsNumeric(homeno.getText());
					if (!istrue) {
						HnoErr.setText("Invalid Number");
						homeno.setText("");
					} else {
						HnoErr.setText("");
					}
				} else {
					HnoErr.setText("Phone No. Should Be 7 Digits");
				}
			}
		}
		if (arg0.getSource() == mobileno) {
			if (mobileno.getText().isEmpty()) {
				MnoErr.setText("Mobile no is Mandatory");
			} else {
				if (mobileno.getText().length() == 10) {
					boolean istrue = vmi.validateIsNumeric(mobileno.getText());
					if (!istrue) {
						MnoErr.setText("Invalid Number");
						mobileno.setText("");
					} else {
						MnoErr.setText("");
						BtnOK.setEnabled(true);
					}
				} else {
					MnoErr.setText("Phone No. Should Be 10 Digits");
				}
			}

		}
		if (arg0.getSource() == office) {
			if (office.getText().isEmpty()) {

				OnoErr.setText("");
			} else {
				if (office.getText().length() == 7) {
					boolean istrue = vmi.validateIsNumeric(office.getText());
					if (!istrue) {
						OnoErr.setText("Invalid Number");
						office.setText("");
					} else {
						OnoErr.setText("");
					}
				} else {
					OnoErr.setText("Phone No. Should Be 7 Digits");
				}
			}
		}
	}
}

class SearchTab extends JDesktopPane implements ActionListener {
	private JLabel searchl;
	private JTextField searchtxt;
	private JButton Btnsearch, Btnreset;
	private JTable jt;
	Contacts con = new Contacts();
	ContactDaoHbm dao = new ContactDAOHbmImpl();
	StringTokenizer token;

	public SearchTab() {

		searchl = new JLabel("Enter Surname to Search :");
		searchtxt = new JTextField(10);
		Btnsearch = new JButton("Search");
		Btnreset = new JButton("Reset");

		searchl.setBounds(10, 10, 150, 30);
		add(searchl);
		searchtxt.setBounds(170, 10, 100, 30);
		add(searchtxt);
		Btnsearch.setBounds(135, 50, 80, 30);
		add(Btnsearch);
		Btnreset.setBounds(220, 50, 80, 30);
		add(Btnreset);

		Btnsearch.addActionListener(this);
		Btnreset.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String ser = "";
		if (arg0.getSource() == Btnsearch) {
			ser = searchtxt.getText();

			ArrayList<Contacts> contacts = dao.searchContact(ser.toUpperCase());
			//System.out.println("asss  "+contacts.get(0).getAllphno());
			
			final String[] colhead = { "Sr No", "Lastname", "First Name",
					"Email ID", "Number" };
			
			final String[][] rowdata = new String[contacts.size()][5];
			for (int x = 0; x < contacts.size(); x++) {
				String[] contactRecord = new String[5];
				contactRecord[0] = x + 1 + "";
				contactRecord[1] = contacts.get(x).getLname();
				contactRecord[2] = contacts.get(x).getFname();
				contactRecord[3] = contacts.get(x).getEmail();
				contactRecord[4] = parsePhoneNos(contacts.get(x).getAllphno());
				rowdata[x] = contactRecord;

			}
			jt = new JTable(rowdata, colhead);

			int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane jsp = new JScrollPane(jt, v, h);
			jsp.setBounds(10, 100, 800, 200);
			add(jsp);
			
			
		}
		if (arg0.getSource() == Btnreset) {
			clear();

		}
	}

	public void clear() {
		searchtxt.setText("");
		final String[] colhead = { "Sr No", "Lastname", "First Name", "Ph No",
				"Email ID" };
		final String[][] rowdata = new String[0][0];
		jt = new JTable(rowdata, colhead);

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(jt, v, h);
		jsp.setBounds(10, 100, 800, 200);
		add(jsp);

	}

	private String parsePhoneNos(
			HashMap<PhoneCategories, ArrayList<String>> phno) {
		String returnPhone = "";
		HashMap<PhoneCategories, ArrayList<String>> allphno = new HashMap<PhoneCategories, ArrayList<String>>();
		allphno = phno;

		Set set = allphno.entrySet();
		Iterator i = set.iterator();
		Map.Entry me;
		while (i.hasNext()) {
			String str = "";
			me = (Map.Entry) i.next();
			Object element  = "";
			Iterator it;
			it = ((List<Contacts>) me.getValue()).iterator();
			while (it.hasNext()) {
				
				element = it.next();
				str += "  "+element;
				
			}
			System.out.println(element);
			returnPhone += me.getKey() + "-" + str + "; ";
			
		}
		return returnPhone;
	}

}
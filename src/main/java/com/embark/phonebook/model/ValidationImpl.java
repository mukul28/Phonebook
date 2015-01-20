package com.embark.phonebook.model;

public class ValidationImpl {
	private String fname, lname, Hno, Mno, Ono, Email;
	public int flag = 0;
	public boolean flagNumeric = false;
	public boolean flagEmId = false;

public boolean validateLength(String input, int minLength, int maxLength) {
		boolean returnValue = false;
		if (minLength > 0) {
			if (input.length() >= minLength && input.length() <= maxLength) {
				returnValue = true;
			}
		} 
		return returnValue;
	}

	public boolean validateIsAlphabetic(String input) {

		char fnm[] = input.toCharArray();
		boolean flag = false;
		for (int i = 0; i < input.length(); i++) {
			if ((int) fnm[i] >= 65 && (int) fnm[i] <= 90) {
				flag = true;
			} else {
				return false;
			}
		}
		return flag;
	}

	public boolean validateIsNumeric(String input) {
		flagNumeric = false;
		try {
			Long Hlen = Long.parseLong(input);
			flagNumeric = true;
		} catch (Exception e) {
			flagNumeric = false;
		}
		return flagNumeric;
	}

	public boolean validateIsAlpanumeric(String input) {
		char email[] = input.toCharArray();

		if (input.contains("@") && input.contains(".")) {
			for (int i = 0; i < input.length(); i++) {
				if (((int) email[i] >= 48 || (int) email[i] <= 57
						|| (int) email[i] <= 65 || (int) email[i] <= 90 || (int) email[i] >= 97
						|| (int) email[i] <= 122)
						|| (input.contains("@") || input.contains("."))) {

					flagEmId = true;
				} else {
					return false;
				}

			}
		}
		return flagEmId;
	}
}

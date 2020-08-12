package com.nisumlatam.userapp.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	//^(?=(?:.*[0-9]){2,})(?=(?:.*[a-z]){1})(?=(?:.*[A-Z]){2}).+$
	//private final static String regex = "^(?=(?:.*[0-9]){2,}).+$";
	//private final static String regex = "^(?=(?:.*[a-z]){1,}).+$";
	private final static String regex = "^(?=(?:.*[A-Z]){2}).+$";

	public static void main(String[] args) {

		String[] strPasswords = {
		        "M12",
		        "My1",
		        "MypassWoSrd1"
		};

		Pattern pattern = Pattern.compile(regex);

		for (String value : strPasswords) {
			Matcher matcher = pattern.matcher(value);

			System.out.println(value + " is " + (matcher.matches() ? "valid" : "invalid"));
		}

	}

}

package com.capgemini.security;

import org.apache.tomcat.util.codec.binary.Base64;

public class PasswordCoder {
	public static String encode(String input) {
		String string = input.concat("eren");
		String encoded = new String(Base64.encodeBase64(string.getBytes()));
		return encoded;
	}

	public static String decode(String encoded) {
		String decoded = new String(Base64.decodeBase64(encoded.getBytes()));
		String string = decoded.substring(0, decoded.length() - 4);
		return string;
	}

	public static boolean matches(String inputPass, String passToMatch) {
		return encode(inputPass.concat("eren")).equals(passToMatch);
	}
}

package com.golddeers.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface ISecurePassword {

	static String createSecurePassword(String password) {

		SecurePassword securePassword = new SecurePassword();
		return securePassword.generateStrongPasswordHash(password);
	}

	static boolean validateSecurePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecurePassword securePassword = new SecurePassword();
		return securePassword.validatePassword(originalPassword, storedPassword);
	}
}

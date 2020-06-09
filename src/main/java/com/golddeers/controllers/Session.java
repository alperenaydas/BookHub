package com.golddeers.controllers;

import java.util.HashMap;
import java.util.Properties;

public class Session {

	public static HashMap<String, String> online = new HashMap<String, String>();

	public static void login(String username, String role) {
		online.put(username, role);
	}

	public static void logout(String username) {

		online.remove(username);
	}

	
}

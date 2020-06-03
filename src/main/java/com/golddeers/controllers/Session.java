package com.golddeers.controllers;

import java.util.HashSet;

public class Session {

	public static HashSet<String> online = new HashSet<String>();

	public static boolean isOnline(String username) {

		return online.contains(username);
	}

	public static void login(String username) {

		online.add(username);
	}

	public static void logout(String username) {

		online.remove(username);
	}
}

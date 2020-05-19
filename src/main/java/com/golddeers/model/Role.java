package com.golddeers.model;

public enum Role {

	Admin, Registered, Undefined;

	public static Role getByName(String role) {

		if (role.toLowerCase().equals("admin")) {

			return Admin;

		} else if (role.toLowerCase().equals("registered")) {

			return Registered;

		} else {

			return Undefined;
		}
	}
}

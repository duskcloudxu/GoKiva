package com.timeWizard.GokivaBackEnd.model;

public class Users {
	protected String UserName;

	public Users(String userName) {
		super();
		UserName = userName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
}

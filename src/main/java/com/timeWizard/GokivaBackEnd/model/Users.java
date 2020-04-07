package com.timeWizard.GokivaBackEnd.model;

public class Users {
	protected String UserName;
	protected String Password;
	protected String FirstName;
	protected String Email;

	public Users(String userName, String password, String firstName, String email) {
		super();
		UserName = userName;
		FirstName = firstName;
		Email = email;
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}

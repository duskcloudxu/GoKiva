package com.timeWizard.GokivaBackEnd.model;

public class Users {
	protected String UserName;
	protected String Password;
	protected String FirstName;
	protected String LastName;

	public Users(String userName, String password, String firstName, String lastName) {
		UserName = userName;
		FirstName = firstName;
		LastName = lastName;
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

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public int hashCode() {
		return this.getUserName().hashCode();
	}

	@Override
	public boolean equals(Object object){
		if(this == object) {
			return true;
		}
		if(object == null || getClass() != object.getClass()) {
			return false;
		}

		Users user = (Users) object;

		return this.getUserName().equals(user.getUserName())
				&& this.getFirstName().equals(user.getFirstName())
				&& this.getLastName().equals(user.getLastName());
	}




}

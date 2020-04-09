package com.timeWizard.GokivaBackEnd.model;

import java.util.Date;

public class Search {
	protected int VisitId;
	protected String UserName;
	protected int LoanId;
	protected Date VisitedTime;
	protected String Category;
	protected String Country;
	protected int PartnerId;


	public int getVisitId() {
		return VisitId;
	}

	public void setVisitId(int visitId) {
		VisitId = visitId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getLoanId() {
		return LoanId;
	}

	public void setLoanId(int loanId) {
		LoanId = loanId;
	}

	public Date getVisitedTime() {
		return VisitedTime;
	}

	public void setVisitedTime(Date visitedTime) {
		VisitedTime = visitedTime;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public int getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(int partnerId) {
		PartnerId = partnerId;
	}
}

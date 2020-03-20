package com.timeWizard.GokivaBackEnd.model;

public class LoanTags {
	protected Loans loan;
	protected Tags tagName;


	public LoanTags(Loans loan, Tags tagName) {
		super();
		this.loan = loan;
		this.tagName = tagName;
	}
	public Loans getLoan() {
		return loan;
	}
	public void setLoan(Loans loan) {
		this.loan = loan;
	}
	public Tags getTag() {
		return tagName;
	}
	public void setTag(Tags tagName) {
		this.tagName = tagName;
	}



}

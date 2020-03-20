package com.timeWizard.GokivaBackEnd.model;

import java.util.Date;



public class Loans {
	protected int LoanId;
	protected String Description;
	protected Date PostedTime;
	protected Date FundTime;
	protected int FundAmount;
	protected Date DisbursedTime;
	protected int DisbursedAmount;
	protected RegionCountry regionCountry;
	protected LoanThemes loanTheme;
	protected Partners partner;


	public Loans(int loanId) {
		LoanId = loanId;
	}
	public Loans(int loanId, String description, Date postedTime, Date fundTime, int fundAmount, Date disbursedTime,
		int disbursedAmount, RegionCountry regionCountry, LoanThemes loanTheme, Partners partner) {
	super();
	LoanId = loanId;
	Description = description;
	PostedTime = postedTime;
	FundTime = fundTime;
	FundAmount = fundAmount;
	DisbursedTime = disbursedTime;
	DisbursedAmount = disbursedAmount;
	this.regionCountry = regionCountry;
	this.loanTheme = loanTheme;
	this.partner = partner;
}
	public int getLoanId() {
		return LoanId;
	}
	public void setLoanId(int loanId) {
		LoanId = loanId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getPostedTime() {
		return PostedTime;
	}
	public void setPostedTime(Date postedTime) {
		PostedTime = postedTime;
	}
	public Date getFundTime() {
		return FundTime;
	}
	public void setFundTime(Date fundTime) {
		FundTime = fundTime;
	}
	public int getFundAmount() {
		return FundAmount;
	}
	public void setFundAmount(int fundAmount) {
		FundAmount = fundAmount;
	}
	public Date getDisbursedTime() {
		return DisbursedTime;
	}
	public void setDisbursedTime(Date disbursedTime) {
		DisbursedTime = disbursedTime;
	}
	public int getDisbursedAmount() {
		return DisbursedAmount;
	}
	public void setDisbursedAmount(int disbursedAmount) {
		DisbursedAmount = disbursedAmount;
	}
	public RegionCountry getRegionCountry() {
		return regionCountry;
	}
	public void setRegionCountry(RegionCountry regionCountry) {
		this.regionCountry = regionCountry;
	}
	public LoanThemes getLoanTheme() {
		return loanTheme;
	}
	public void setLoanTheme(LoanThemes loanTheme) {
		this.loanTheme = loanTheme;
	}
	public Partners getPartner() {
		return partner;
	}
	public void setPartner(Partners partner) {
		this.partner = partner;
	}


}

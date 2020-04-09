package com.timeWizard.GokivaBackEnd.model;

public class Search {
	protected int VisitId;
	protected int TimesVisited;
	protected Users user;
	protected Loans loan;
	public Search(int visitId, int timesVisited, Users user, Loans loan) {
		super();
		VisitId = visitId;
		TimesVisited = timesVisited;
		this.user = user;
		this.loan = loan;
	}

	public Search(int visitId) {
		super();
		VisitId = visitId;
	}

	public Search(int timesVisited, Users user, Loans loan) {
		super();
		TimesVisited = timesVisited;
		this.user = user;
		this.loan = loan;
	}

	public int getVisitId() {
		return VisitId;
	}
	public void setVisitId(int visitId) {
		VisitId = visitId;
	}
	public int getTimesVisited() {
		return TimesVisited;
	}
	public void setTimesVisited(int timesVisited) {
		TimesVisited = timesVisited;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Loans getLoan() {
		return loan;
	}
	public void setLoan(Loans loan) {
		this.loan = loan;
	}

	@Override
	public int hashCode() {
		return user.getUserName().hashCode() * 37
				+ loan.getLoanId();
	}

	@Override
	public boolean equals(Object object){
			if(this == object) {
				return true;
			}
			if(object == null || getClass() != object.getClass()) {
				return false;
			}

			Search search = (Search) object;

			return this.getUser().equals(search.getUser())
					&& this.getLoan().equals(search.getLoan());
		}




	}

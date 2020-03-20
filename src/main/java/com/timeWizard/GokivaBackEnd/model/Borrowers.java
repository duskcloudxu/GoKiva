package com.timeWizard.GokivaBackEnd.model;

public class Borrowers {

  protected int BorrowerId;
  protected String Gender;

  public Borrowers(int borrowerId, String gender) {

    BorrowerId = borrowerId;
    Gender = gender;
  }


  public Borrowers(int borrowerId) {
    super();
    BorrowerId = borrowerId;
  }


  public Borrowers(String gender) {
    super();
    Gender = gender;
  }


  public int getBorrowerId() {
    return BorrowerId;
  }

  public void setBorrowerId(int borrowerId) {
    BorrowerId = borrowerId;
  }

  public String getGender() {
    return Gender;
  }

  public void setGender(String gender) {
    Gender = gender;
  }

	@Override
	public String toString() {
		return "Borrowers{" +
				"BorrowerId=" + BorrowerId +
				", Gender='" + Gender + '\'' +
				'}';
	}
}

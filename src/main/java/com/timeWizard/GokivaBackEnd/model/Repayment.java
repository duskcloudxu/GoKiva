package com.timeWizard.GokivaBackEnd.model;



public class Repayment {

	protected int RepaymentId;
	protected RepaymentType RepaymentType;
	protected Loans loan;
	protected Borrowers borrower;

	public enum RepaymentType {
		monthly, irregular, bullet;
	}

	public Repayment(int repaymentId, RepaymentType repaymentType, Loans loan, Borrowers borrower) {
		super();
		RepaymentId = repaymentId;

		this.loan = loan;
		this.borrower = borrower;
		RepaymentType = repaymentType;
	}

	public Repayment(Loans loan, Borrowers borrower, RepaymentType repaymentType) {
		super();

		this.loan = loan;
		this.borrower = borrower;
		RepaymentType = repaymentType;
	}

	public Repayment(int repaymentId) {
		super();
		RepaymentId = repaymentId;
	}

	public int getRepaymentId() {
		return RepaymentId;
	}

	public void setRepaymentId(int repaymentId) {
		RepaymentId = repaymentId;
	}

	public RepaymentType getRepaymentType() {
		return RepaymentType;
	}

	public void setRepaymentType(RepaymentType repaymentType) {
		RepaymentType = repaymentType;
	}

	public Loans getLoan() {
		return loan;
	}

	public void setLoan(Loans loan) {
		this.loan = loan;
	}

	public Borrowers getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrowers borrower) {
		this.borrower = borrower;
	}


}

package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import  com.timeWizard.GokivaBackEnd.model.*;

public class RepaymentDao {
protected ConnectionManager connectionManager;

	private static RepaymentDao instance = null;
	protected RepaymentDao() {
		connectionManager = new ConnectionManager();
	}
	public static RepaymentDao getInstance() {
		if(instance == null) {
			instance = new RepaymentDao();
		}
		return instance;
	}


	public Repayment create(Repayment repayment) throws SQLException {
		String insertRepayment = "INSERT INTO Repayment(BorrowerId,LoanId,RepaymentType) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRepayment,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt = connection.prepareStatement(insertRepayment);
			insertStmt.setInt(1, repayment.getBorrower().getBorrowerId());
			insertStmt.setInt(2, repayment.getLoan().getLoanId());
			insertStmt.setString(3, repayment.getRepaymentType().name());

			insertStmt.executeUpdate();

			return repayment;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Repayment delete(Repayment repayment) throws SQLException {
		String deleteRepayment = "DELETE FROM Repayment WHERE RepaymentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRepayment);
			deleteStmt.setInt(1, repayment.getRepaymentId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Repayment getRepaymentById(int repaymentId) throws SQLException {
		String selectRepayment =
			"SELECT RepaymentId, BorrowerId, LoanId, RepaymentType " +
			"FROM Repayment " +
			"WHERE RepaymentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRepayment);
			selectStmt.setInt(1, repaymentId);
			results = selectStmt.executeQuery();
			BorrowersDao borrowersDao = BorrowersDao.getInstance();
			LoansDao loansDao = LoansDao.getInstance();
			if(results.next()) {
				int resultRepaymentIdInt = results.getInt("RepaymentId");
				int borrowerId = results.getInt("BorrowerId");
				int loanId = results.getInt("LoanId");
				Repayment.RepaymentType repaymentType = Repayment.RepaymentType.valueOf(results.getString("RepaymentType"));

				Borrowers borrower = borrowersDao.getBorrowersById(borrowerId);
				Loans loan = loansDao.getLoansById(loanId);

				Repayment repayment = new Repayment(resultRepaymentIdInt,repaymentType,loan, borrower);
				return repayment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

}


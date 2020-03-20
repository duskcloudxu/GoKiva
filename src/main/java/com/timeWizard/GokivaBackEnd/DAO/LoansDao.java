package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import com.timeWizard.GokivaBackEnd.model.LoanThemes;
import com.timeWizard.GokivaBackEnd.model.Loans;
import com.timeWizard.GokivaBackEnd.model.Partners;
import com.timeWizard.GokivaBackEnd.model.RegionCountry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoansDao {
protected ConnectionManager connectionManager;

	private static LoansDao instance = null;
	protected LoansDao() {
		connectionManager = new ConnectionManager();
	}
	public static LoansDao getInstance() {
		if(instance == null) {
			instance = new LoansDao();
		}
		return instance;
	}


	public Loans create(Loans loan) throws SQLException {
		String insertLoans = "INSERT INTO Loans(LoanId,Description,PostedTime,FundTime,FundAmount,DisbursedTime,DisbursedAmount,RegionCountry,PartnerId,ThemeId) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLoans);
			insertStmt.setInt(1, loan.getLoanId());
			insertStmt.setString(2, loan.getDescription());
		    insertStmt.setTimestamp(3, new Timestamp(loan.getPostedTime().getTime()));
		    insertStmt.setTimestamp(4, new Timestamp(loan.getFundTime().getTime()));
		    insertStmt.setInt(5, loan.getFundAmount());
		    insertStmt.setTimestamp(6, new Timestamp(loan.getDisbursedTime().getTime()));
		    insertStmt.setInt(7, loan.getDisbursedAmount());
		    insertStmt.setString(8, loan.getRegionCountry().getRegionCountryId());
		    insertStmt.setString(9, loan.getPartner().getPartnerId());
		    insertStmt.setString(10, loan.getLoanTheme().getThemeId());

			insertStmt.executeUpdate();

			return loan;
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

	public Loans updateDescription(Loans loan, String newDescription) throws SQLException {
		String updateLoan = "UPDATE Loans SET Description=? WHERE loanId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLoan);
			updateStmt.setString(1, newDescription);
			updateStmt.setString(2, String.valueOf(loan.getLoanId()));
			updateStmt.executeUpdate();

			// Update the person param before returning to the caller.
			loan.setDescription(newDescription);
			return loan;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	public Loans delete(Loans loan) throws SQLException {
		String deleteLoans = "DELETE FROM Loans WHERE LoanId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLoans);
			deleteStmt.setInt(1, loan.getLoanId());
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

	public Loans getLoansById(int loanId) throws SQLException {
		String selectLoans =
			"SELECT LoanId, Description, PostedTime, FundTime, FundAmount, DisbursedTime, DisbursedAmount, RegionCountry, PartnerId, ThemeId " +
			"FROM Loans " +
			"WHERE LoanId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLoans);
			selectStmt.setInt(1, loanId);
			results = selectStmt.executeQuery();
			RegionCountryDao regionCountryDao = RegionCountryDao.getInstance();
			PartnersDao partnersDao = PartnersDao.getInstance();
			LoanThemesDao loanThemesDao = LoanThemesDao.getInstance();
			if(results.next()) {
				int resultLoansInt = results.getInt("LoanId");
				String description = results.getString("Description");
				Date postedTime =  new Date(results.getTimestamp("PostedTime").getTime());
				Date fundTime =  new Date(results.getTimestamp("FundTime").getTime());
				int fundAmount =  results.getInt("FundAmount");
				Date disbursedTime =  null;
				int disbursedAmount = results.getInt("DisbursedAmount");
				String regionCountryId = results.getString("RegionCountry");
				String partnerId = results.getString("PartnerId");
				String themeId = results.getString("ThemeId");


				RegionCountry regionCountry = regionCountryDao.getRegionCountryById(regionCountryId);
				Partners partner = partnersDao.getPartnersById(partnerId);
				LoanThemes loanTheme = loanThemesDao.getLoanThemesById(themeId);
				Loans loan = new Loans(resultLoansInt,description,postedTime,fundTime,fundAmount,disbursedTime,disbursedAmount,regionCountry,loanTheme, partner);
				return loan;
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

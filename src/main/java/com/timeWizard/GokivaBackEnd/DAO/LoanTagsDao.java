package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import  com.timeWizard.GokivaBackEnd.model.*;
public class LoanTagsDao {
protected ConnectionManager connectionManager;

	private static LoanTagsDao instance = null;
	protected LoanTagsDao() {
		connectionManager = new ConnectionManager();
	}
	public static LoanTagsDao getInstance() {
		if(instance == null) {
			instance = new LoanTagsDao();
		}
		return instance;
	}


	public LoanTags create(LoanTags loanTag) throws SQLException {
		String insertLoanTags = "INSERT INTO LoanTags(LoanId,TagName) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLoanTags);
			insertStmt.setInt(1, loanTag.getLoan().getLoanId());
			insertStmt.setString(2, loanTag.getTag().getTagName());

			insertStmt.executeUpdate();

			return loanTag;
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

	public LoanTags delete(LoanTags loanTag) throws SQLException {
		String deleteLoanTags = "DELETE FROM LoanTags WHERE LoanId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLoanTags);
			deleteStmt.setInt(1, loanTag.getLoan().getLoanId());
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

	public LoanTags getLoanTagsById(int loanTagId) throws SQLException {
		String selectLoanTags =
			"SELECT LoanId, TagName " +
			"FROM LoanTags " +
			"WHERE LoanId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLoanTags);
			selectStmt.setInt(1, loanTagId);
			results = selectStmt.executeQuery();
			TagsDao tagsDao = TagsDao.getInstance();
			LoansDao loansDao = LoansDao.getInstance();
			if(results.next()) {
				int resultLoanTagsIdInt = results.getInt("LoanId");

				String tagName = results.getString("TagName");


				Loans loan = loansDao.getLoansById(resultLoanTagsIdInt);
				Tags tag = tagsDao.getTagByTagName(tagName);

				LoanTags loanTag = new LoanTags(loan,tag);
				return loanTag;
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



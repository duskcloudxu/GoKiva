package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import  com.timeWizard.GokivaBackEnd.model.*;

public class LoanThemesDao {
protected ConnectionManager connectionManager;

	private static LoanThemesDao instance = null;
	protected LoanThemesDao() {
		connectionManager = new ConnectionManager();
	}
	public static LoanThemesDao getInstance() {
		if(instance == null) {
			instance = new LoanThemesDao();
		}
		return instance;
	}


	public LoanThemes create(LoanThemes loanTheme) throws SQLException {
		String insertLoanThemes = "INSERT INTO LoanThemes(ThemeId,Theme) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLoanThemes);
			insertStmt.setString(1, loanTheme.getThemeId());
			insertStmt.setString(2, loanTheme.getTheme());
	;


			insertStmt.executeUpdate();

			return loanTheme;
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

	public LoanThemes delete(LoanThemes loanTheme) throws SQLException {
		String deleteLoanThemes = "DELETE FROM LoanThemes WHERE ThemeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLoanThemes);
			deleteStmt.setString(1, loanTheme.getThemeId());
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

	public LoanThemes getLoanThemesById(String ThemeId) throws SQLException {
		String selectLoanThemes =
			"SELECT ThemeId,Theme " +
			"FROM LoanThemes " +
			"WHERE ThemeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLoanThemes);
			selectStmt.setString(1, ThemeId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultThemeIdString = results.getString("ThemeId");
				String theme = results.getString("Theme");



				LoanThemes loanTheme = new LoanThemes(resultThemeIdString, theme);
				return loanTheme;
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


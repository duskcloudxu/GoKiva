package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import  com.timeWizard.GokivaBackEnd.model.*;


public class SearchDao {
	protected ConnectionManager connectionManager;

	private static SearchDao instance = null;
	protected SearchDao() {
		connectionManager = new ConnectionManager();
	}
	public static SearchDao getInstance() {
		if(instance == null) {
			instance = new SearchDao();
		}
		return instance;
	}


	public Search create(Search search) throws SQLException {
		String insertSearch = "INSERT INTO Search(TimesVisited,UserName,LoanId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSearch,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt = connection.prepareStatement(insertSearch);
			insertStmt.setInt(1, search.getTimesVisited());
			insertStmt.setString(2, search.getUser().getUserName());
			insertStmt.setInt(3, search.getLoan().getLoanId());



			insertStmt.executeUpdate();

			return search;
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

	public Search delete(Search search) throws SQLException {
		String deleteSearch = "DELETE FROM Search WHERE VisitId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSearch);
			deleteStmt.setInt(1, search.getVisitId());
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

	public Search getSearchById(int visitId) throws SQLException {
		String selectSearch =
			"SELECT visitId, timesVisited, userName, loanId " +
			"FROM Search " +
			"WHERE VisitId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSearch);
			selectStmt.setInt(1, visitId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			LoansDao loansDao = LoansDao.getInstance();
			if(results.next()) {
				int resultVisitedIdInt = results.getInt("VisitId");
				int timesVisited = results.getInt("TimesVisited");
				String userName = results.getString("UserName");
				int loanId = results.getInt("LoanId");


				Users user = usersDao.getUsersByUserName(userName);
				Loans loan = loansDao.getLoansById(loanId);



				Search search = new Search(resultVisitedIdInt, timesVisited,user,loan);
				return search;
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


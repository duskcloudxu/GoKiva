package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import  com.timeWizard.GokivaBackEnd.model.*;


public class SearchDao {
	protected ConnectionManager connectionManager;

	private static SearchDao instance = null;

	protected SearchDao() {
		connectionManager = new ConnectionManager();
	}

	public static SearchDao getInstance() {
		if (instance == null) {
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
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Search delete(Search search) throws SQLException {
		String deleteSearch = "DELETE FROM Search WHERE UserName=? and LoanId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSearch);
			deleteStmt.setString(1, search.getUser().getUserName());
			deleteStmt.setInt(2, search.getLoan().getLoanId());

			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}


	public HashMap<Loans, Integer> getSearchByUserName(String userName) throws SQLException {
		String selectSearch =
				"SELECT visitId, timesVisited, userName, loanId " +
						"FROM Search " +
						"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		HashMap<Loans, Integer> searchMap = new HashMap<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSearch);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			LoansDao loansDao = LoansDao.getInstance();

			while (results.next()) {

				int visitId = results.getInt("VisitId");
				int timesVisited = results.getInt("TimesVisited");
				String resultUserName = results.getString("UserName");
				int loanId = results.getInt("LoanId");


				Users user = usersDao.getUsersByUserName(resultUserName);
				Loans loan = loansDao.getLoansById(loanId);

				Search search = new Search(visitId, timesVisited, user, loan);
				searchMap.put(search.getLoan(), search.getTimesVisited());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return searchMap;
	}

	public Search getSearchByLoanId(int loanId) throws SQLException {
		String selectSearch =
				"SELECT visitId, timesVisited, userName, loanId " +
						"FROM Search " +
						"WHERE LoanId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		HashMap<Loans, Integer> searchMap = new HashMap<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSearch);
			selectStmt.setInt(1, loanId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			LoansDao loansDao = LoansDao.getInstance();

			if (results.next()) {

				int timesVisited = results.getInt("TimesVisited");


				Users user = usersDao.getUsersByUserName(results.getString("UserName"));
				Loans loan = loansDao.getLoansById(results.getInt("LoanId"));

				return new Search(timesVisited, user, loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	public Search updateTimesVisited(int timesVisited, Users user, Loans loan) throws SQLException {
			String updateSearch = "UPDATE Search SET TimesVisited=? WHERE Users=? and LoanId=?;";
			Connection connection = null;
			PreparedStatement updateStmt = null;

			try {
				connection = connectionManager.getConnection();
				updateStmt = connection.prepareStatement(updateSearch);
				updateStmt.setInt(1, timesVisited);
				updateStmt.setString(2, user.getUserName());
				updateStmt.setInt(3, loan.getLoanId());
				updateStmt.executeUpdate();

				return new Search(timesVisited, user, loan);
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


	public HashMap<Loans, Integer> saveSearchResults(Users user, ArrayList<Loans> loans) throws SQLException {
		HashMap<Loans, Integer> previousLoans = getSearchByUserName(user.getUserName());
		for (Loans loan : loans) {
			int increment = 0;
			if (previousLoans.containsKey(loan)) {


				increment += previousLoans.get(loan);
				previousLoans.put(loan, increment);
				updateTimesVisited(increment, user, loan);

			} else {
				create(new Search(0, user, loan));
			}
		}

		return getSearchByUserName(user.getUserName());
	}




}


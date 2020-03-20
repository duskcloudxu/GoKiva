package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import com.timeWizard.GokivaBackEnd.model.Borrowers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BorrowersDao {
protected ConnectionManager connectionManager;

	private static BorrowersDao instance = null;
	protected BorrowersDao() {
		connectionManager = new ConnectionManager();
	}
	public static BorrowersDao getInstance() {
		if(instance == null) {
			instance = new BorrowersDao();
		}
		return instance;
	}


	public Borrowers create(Borrowers borrower) throws SQLException {
		String insertBorrowers = "INSERT INTO Borrowers(Gender) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBorrowers,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt = connection.prepareStatement(insertBorrowers);
			insertStmt.setString(1, borrower.getGender());



			insertStmt.executeUpdate();

			return borrower;
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

	public Borrowers delete(Borrowers borrower) throws SQLException {
		String deleteBorrowers = "DELETE FROM Borrowers WHERE BorrowerId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBorrowers);
			deleteStmt.setInt(1, borrower.getBorrowerId());
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

	public Borrowers getBorrowersById(int borrowerId) throws SQLException {
		String selectBorrowers =
			"SELECT BorrowerId,Gender " +
			"FROM Borrowers " +
			"WHERE BorrowerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();


			selectStmt = connection.prepareStatement(selectBorrowers,
					Statement.RETURN_GENERATED_KEYS);
			selectStmt.setInt(1, borrowerId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultBorrowersInt = results.getInt("BorrowerId");
				String gender = results.getString("Gender");



				Borrowers borrower = new Borrowers(resultBorrowersInt, gender);
				return borrower;
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

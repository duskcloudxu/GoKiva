package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import  com.timeWizard.GokivaBackEnd.model.*;

public class UsersDao {
protected ConnectionManager connectionManager;
protected SecureRandom random;

	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
		random = new SecureRandom();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	/**
	 * Save the Users instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Users create(Users user) throws SQLException {
		String insertPerson = "INSERT INTO Users(UserName,Password,FirstName,LastName) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);

			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());


			insertStmt.executeUpdate();


			return user;
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


	public Users delete(Users user) throws SQLException {
		String deletePerson = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, user.getUserName());
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

	/**
	 * Get the matching Users records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Users.
	 */
	public Users getUsersByUserName(String userName) throws SQLException {
		//Users users = new Users();
		String selectUsers =
			"SELECT * FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");



				Users user = new Users(resultUserName, password, firstName, lastName);
				return user;
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

	public boolean authenticatePassword(String username, String password) throws SQLException,
			NoSuchAlgorithmException, InvalidKeySpecException {
		Users user = getUsersByUserName(username);
		String hashed = hashPassword(username, password);
		return hashed == user.getPassword();

	}

	public String hashPassword(String userName, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = userName.getBytes();
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,  65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		System.out.printf("salt: %s%n", enc.encodeToString(salt));
		System.out.printf("hash: %s%n", enc.encodeToString(hash));
		return enc.encodeToString(hash);
	}

	public Users createAccount(String userName, String password, String retypedPassword, String firstName, String lastName)
			throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException{
		if (password == retypedPassword) {
			String hashed = hashPassword(userName, password);

			return create(new Users(userName, hashed, firstName, lastName));
		} else {
			System.err.println("Passwords do not match.");
			return null;
		}
	}

}

package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import  com.timeWizard.GokivaBackEnd.model.*;


public class TagsDao {
protected ConnectionManager connectionManager;


	private static TagsDao instance = null;
	protected TagsDao() {
		connectionManager = new ConnectionManager();
	}
	public static TagsDao getInstance() {
		if(instance == null) {
			instance = new TagsDao();
		}
		return instance;
	}

	/**
	 * Save the Tags instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Tags create(Tags tag) throws SQLException {
		String insertPerson = "INSERT INTO Tags(TagName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);

			insertStmt.setString(1, tag.getTagName());

			insertStmt.executeUpdate();


			return tag;
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


	public Tags delete(Tags tag) throws SQLException {
		String deletePerson = "DELETE FROM Tags WHERE TagName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, tag.getTagName());
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
	 * Get the Tags record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Tags instance.
	 */
	public Tags getTagByTagName(String tagName) throws SQLException {
		String selectTag = "SELECT TagName FROM Tags WHERE TagName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTag);
			selectStmt.setString(1, tagName);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultTagName = results.getString("TagName");

				Tags tag = new Tags(resultTagName);
				return tag;
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


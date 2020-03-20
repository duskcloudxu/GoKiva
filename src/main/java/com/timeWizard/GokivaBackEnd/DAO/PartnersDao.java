package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import  com.timeWizard.GokivaBackEnd.model.*;


public class PartnersDao {
protected ConnectionManager connectionManager;

	private static PartnersDao instance = null;
	protected PartnersDao() {
		connectionManager = new ConnectionManager();
	}
	public static PartnersDao getInstance() {
		if(instance == null) {
			instance = new PartnersDao();
		}
		return instance;
	}


	public Partners create(Partners partner) throws SQLException {
		String insertPartners = "INSERT INTO Partners(PartnerId) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPartners);
			insertStmt.setString(1, partner.getPartnerId());



			insertStmt.executeUpdate();

			return partner;
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

	public Partners delete(Partners partner) throws SQLException {
		String deletePartners = "DELETE FROM Partners WHERE PartnerId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePartners);
			deleteStmt.setString(1, partner.getPartnerId());
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

	public Partners getPartnersById(String partnerId) throws SQLException {
		String selectPartners =
			"SELECT PartnerId " +
			"FROM Partners " +
			"WHERE PartnerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPartners);
			selectStmt.setString(1, partnerId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultPartnersString = results.getString("PartnerId");



				Partners partner = new Partners(resultPartnersString);
				return partner;
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

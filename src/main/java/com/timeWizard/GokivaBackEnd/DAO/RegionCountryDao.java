package com.timeWizard.GokivaBackEnd.DAO;

import com.timeWizard.GokivaBackEnd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import  com.timeWizard.GokivaBackEnd.model.*;




public class RegionCountryDao {
	protected ConnectionManager connectionManager;

	private static RegionCountryDao instance = null;
	protected RegionCountryDao() {
		connectionManager = new ConnectionManager();
	}
	public static RegionCountryDao getInstance() {
		if(instance == null) {
			instance = new RegionCountryDao();
		}
		return instance;
	}


	public RegionCountry create(RegionCountry regionCountry) throws SQLException {
		String insertRegionCountry = "INSERT INTO RegionCountry(RegionCountryId,Region,Country,CountryCode) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRegionCountry);
			insertStmt.setString(1, regionCountry.getRegionCountryId());
			insertStmt.setString(2, regionCountry.getRegion());
			insertStmt.setString(3, regionCountry.getCountry());
			insertStmt.setString(4, regionCountry.getCountryCode());


			insertStmt.executeUpdate();

			return regionCountry;
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

	public RegionCountry delete(RegionCountry regionCountry) throws SQLException {
		String deleteRegionCountry = "DELETE FROM RegionCountry WHERE RegionCountryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRegionCountry);
			deleteStmt.setString(1, regionCountry.getRegionCountryId());
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

	public RegionCountry getRegionCountryById(String regionCountryId) throws SQLException {
		String selectRegionCountry =
			"SELECT RegionCountryId,Region,Country,CountryCode " +
			"FROM RegionCountry " +
			"WHERE RegionCountryId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRegionCountry);
			selectStmt.setString(1, regionCountryId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultRegionCountryString = results.getString("RegionCountryId");
				String region = results.getString("Region");
				String country = results.getString("Country");
				String countryCode = results.getString("countryCode");


				RegionCountry regionCountry = new RegionCountry(resultRegionCountryString, region, country, countryCode);
				return regionCountry;
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

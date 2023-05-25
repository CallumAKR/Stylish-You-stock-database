package com.drisq.minimalGraphicsProject.fx;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.drisq.util.AppJarLocUtil;

public class Database {

	private static Connection connection;

	// Connects to the database

	public static Connection getConnection() {
		File installDir = getInstallDir();
		File dbFile = new File(installDir, "db/OurProducts.sqlite");
		try {
			if (connection != null) {
				connection.close();
			}
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getPath());
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private static File getInstallDir() {
		AppJarLocUtil util = null;
		File appJarFile = null;

		try {
			util = AppJarLocUtil.getInstance(Database.class);
		} catch (IOException e) {
			throw new InternalError("Cannot locate own application jar file.");
		}

		try {
			appJarFile = util.getFileLocation().getCanonicalFile();
		} catch (IOException e) {
			throw new InternalError("Cannot establish canonical location to installation dir.");
		}

		return appJarFile.getParentFile();

	}

	// creates a result set using the stated query

	public static ResultSet mensQuery(String mensQuery) {

		try {
			// connects

			connection = getConnection();

			// creates a statement of the query string

			PreparedStatement statement = connection.prepareStatement(mensQuery);

			// creates a result set equal to the executed query results

			ResultSet mensSet = statement.executeQuery();

			return mensSet;

		}

		// a catch if the query has an error

		catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + mensQuery);
		}

	}

	public static ResultSet womensQuery(String womensQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(womensQuery);

			ResultSet womensSet = statement.executeQuery();

			return womensSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + womensQuery);
		}

	}

	public static ResultSet boysQuery(String boysQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(boysQuery);

			ResultSet boysSet = statement.executeQuery();

			return boysSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + boysQuery);
		}

	}

	public static ResultSet girlsQuery(String girlsQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(girlsQuery);

			ResultSet girlsSet = statement.executeQuery();

			return girlsSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + girlsQuery);
		}

	}

	public static ResultSet zaraQuery(String zaraQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(zaraQuery);

			ResultSet zaraSet = statement.executeQuery();

			return zaraSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + zaraQuery);
		}

	}

	public static ResultSet jackWillsQuery(String jackWillsQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(jackWillsQuery);

			ResultSet jackWillsSet = statement.executeQuery();

			return jackWillsSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + jackWillsQuery);
		}

	}

	public static ResultSet hollisterQuery(String hollisterQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(hollisterQuery);

			ResultSet hollisterSet = statement.executeQuery();

			return hollisterSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + hollisterQuery);
		}

	}

	public static ResultSet productTypeQuery(String productTypeQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(productTypeQuery);

			ResultSet productTypeSet = statement.executeQuery();

			return productTypeSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + productTypeQuery);
		}

	}
}
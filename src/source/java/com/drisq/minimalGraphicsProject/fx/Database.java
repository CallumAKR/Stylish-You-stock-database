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

	/**
	 * Gets a connection to the software update database
	 * 
	 * @return The connection to the software update database
	 */
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

	public static ResultSet mensQuery(String mensQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(mensQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + mensQuery);
		}

	}

	public static ResultSet womensQuery(String womensQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(womensQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + womensQuery);
		}

	}

	public static ResultSet boysQuery(String boysQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(boysQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + boysQuery);
		}

	}

	public static ResultSet girlsQuery(String girlsQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(girlsQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + girlsQuery);
		}

	}

	public static ResultSet zaraQuery(String zaraQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(zaraQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + zaraQuery);
		}

	}

	public static ResultSet jackWillsQuery(String jackWillsQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(jackWillsQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + jackWillsQuery);
		}

	}

	public static ResultSet hollisterQuery(String hollisterQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(hollisterQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + hollisterQuery);
		}

	}

	public static ResultSet productsQuery(String productsQuery) {

		try {
			connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(productsQuery);

			ResultSet testSet = statement.executeQuery();

			return testSet;

		} catch (SQLException e) {
			throw new InternalError("Cannot execute queries : " + productsQuery);
		}

	}
}
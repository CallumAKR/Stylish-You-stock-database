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
}
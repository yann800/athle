package fr.yann.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

public class Insert {

	private static final String	DRIVER_NAME	= "com.mysql.jdbc.Driver";
	private static final String	URL			= "jdbc:mysql://localhost:3306/athle";
	private static final String	USER		= "root";
	private static final String	PASSWORD	= "";

	public static void main(String path) {

		try {
			Class.forName(DRIVER_NAME).newInstance();

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con);

			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(path));

			// Exctute script
			sr.runScript(reader);

			con.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

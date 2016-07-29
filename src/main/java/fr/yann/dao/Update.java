package fr.yann.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Update {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String	URL			= "jdbc:mysql://localhost:3306/athle";
	private static final String	USER			= "root";
	private static final String	PASSWORD		= "";

	public static void updateAnnee() {

		try {
			Class.forName(DRIVER_NAME).newInstance();

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

			con.createStatement().executeUpdate("UPDATE ligne SET annee = 2000 + SUBSTRING(datePerf,7, 8)");

			// con.createStatement().executeUpdate("UPDATE ligne l SET l.points = (SELECT c.points FROM Cotation c WHERE c.idEpreuve = l.idEpreuve AND c.sexe = l.sexe AND l.perf = c.perf)");

			con.createStatement().executeUpdate("UPDATE ligne l SET naissance = naissance + 1900 WHERE naissance > 30 and naissance <= 99");

			con.createStatement().executeUpdate("UPDATE ligne l SET naissance = naissance + 2000 WHERE naissance < 31 and naissance <= 99");

			con.createStatement().executeUpdate("UPDATE  ligne SET perf = CONCAT('0', perf) where idEpreuve = 3000 and (perf like '7%' OR  perf like '8%' OR perf like '9%')");

			con.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public static void updatePays() {

		try {
			Class.forName(DRIVER_NAME).newInstance();

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

			// for (ligneStranger)

			con.createStatement().executeUpdate("UPDATE ligne SET pays = WHERE id = ");

			con.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}

package fr.yann.parser.record_wiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;
import fr.yann.model.record.LigneRecordWiki;

public class E4_ComputeRangEpreuve {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/athle";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) throws SQLException {

		List<LigneRecordWiki> liste = null;

		EpreuveEnum epreuve = EpreuveEnum.COURSE_100;

		Connection con = null;

		try {
			Class.forName(DRIVER_NAME).newInstance();

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			liste = getRecordsEpreuveSexe(epreuve, SexeEnum.MASCULIN, con);

			con.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			if (con != null) {
				con.close();
			}
		}
		int rang = 1;
		for (LigneRecordWiki r : liste) {
			System.out.println(r.getIdPays() + " " + rang);
			rang++;
		}

	}

	private static List<LigneRecordWiki> getRecordsEpreuveSexe(EpreuveEnum epreuve, SexeEnum sexe, Connection con) throws SQLException {

		List<LigneRecordWiki> liste = new ArrayList<>();

		Statement stmt = con.createStatement();
		
		ResultSet resultSet = stmt.executeQuery("SELECT r.idPays, r.perf FROM record r "
				+ " WHERE r.epreuve = " + epreuve.getCode() + " AND r.sexe = " + sexe.getCodeInt()
				+ " ORDER BY r.perf ");

		// ORDER BY CONCAT( REPEAT(  "0", 18 - LENGTH( stringfield ) ) , stringfield )
		// order by length(col),col

		while (resultSet.next()) {

			int idPays = resultSet.getInt(1);
			String perf = resultSet.getString(2);
			LigneRecordWiki r = new LigneRecordWiki(idPays, sexe);
			r.setPerf(perf);
			liste.add(r);
		}
		
		stmt.close();

		return liste;
		
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}



}

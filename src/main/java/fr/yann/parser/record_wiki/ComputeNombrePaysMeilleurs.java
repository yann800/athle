package fr.yann.parser.record_wiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class ComputeNombrePaysMeilleurs {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/athle";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) throws SQLException {

		int idPays = 69;

		EpreuveEnum epreuve = EpreuveEnum.COURSE_100;

		Connection con = null;

		try {
			Class.forName(DRIVER_NAME).newInstance();

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			updateRangPaysEpreuveSexe(idPays, epreuve, SexeEnum.MASCULIN, con);

			con.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			if (con != null) {
				con.close();
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	private static void updateRangPaysEpreuveSexe(int idPays, EpreuveEnum epreuve,
			SexeEnum sexe, Connection con) throws Exception {
		Statement stmt = con.createStatement();

		final String clausesEpreuvePaysSexe = " WHERE r.epreuve = '" + epreuve.getCode() + "' AND r.idPays = " + idPays + " AND r.sexe = " + sexe.getCodeInt();
		
		ResultSet resultSet = stmt.executeQuery("SELECT count(*) AS nb FROM record r " + clausesEpreuvePaysSexe + getClausePerf(epreuve, clausesEpreuvePaysSexe));

		int nombrePaysMeilleurs = -1;

		while (resultSet.next()) {

			nombrePaysMeilleurs = resultSet.getInt("nb");

		}
		
		// le nombre de lignes affectées dans le cas d’un insert, d’un update
		int i = stmt.executeUpdate("UPDATE record r SET r.rang = " + nombrePaysMeilleurs + clausesEpreuvePaysSexe);

		System.out.println("UPDATE record r SET r.rang = " + nombrePaysMeilleurs + clausesEpreuvePaysSexe);
		
		stmt.close();

		if (i != 1) {
			throw new Exception("upddate " + i + " pour " + clausesEpreuvePaysSexe);
		}
	}

	private static String getClausePerf(EpreuveEnum epreuve, String clausesEpreuvePaysSexe) {
		String perf = "(SELECT recordSelectedPays.perf FROM record recordSelectedPays " + clausesEpreuvePaysSexe + ")";
		if (epreuve.name().startsWith("COURSE")){
			return " AND r.perf < " + perf ;
		}
		return " AND r.perf > " + perf;
	}

}

package fr.yann.graph;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import fr.yann.model.Result;

public class Compare {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/athle",
				"root", "");

		String query = "SELECT nom nom, annee annee, MAX(rang) rang FROM ligne WHERE (nom IN ('ABDELKADER', 'LACHEREST', 'DIANI') or (nom = 'ANDRIEU' and prenom = 'olivier'))"
					+ "AND annee > 2009 GROUP BY nom, annee ORDER BY annee, nom";


		// create the java statement
		Statement st = (Statement) conn.createStatement();

		// execute the query, and get a java resultset
		ResultSet rs = st.executeQuery(query);

		// ['Year', 'ABDELKADER', 'ANDRIEU', 'LACHEREST', 'DIANI'],
		// ['2010', 712,			 622, 984, 806],

		List<Result> results = new ArrayList<>();

		List<String> noms = new ArrayList<>();
		noms.add("ABDELKADER");
		noms.add("ANDRIEU");
		noms.add("DIANI");
		noms.add("LACHEREST");

		// iterate through the java resultset
		while (rs.next()) {
			String nom = rs.getString("nom");
			int annee = rs.getInt("annee");
			int rang = rs.getInt("rang");

			// print the results
			// System.out.format("%s, %s, %s\n", nom, annee, rang);

			results.add(new Result(nom, annee, rang));
		}
		st.close();

		StringBuffer sb = new StringBuffer();

		List<Integer> annees = new ArrayList<Integer>();
		annees.add(2010);
		annees.add(2011);
		annees.add(2012);
		annees.add(2013);
		annees.add(2014);
		annees.add(2015);
		annees.add(2016);


		for (Integer a : annees) {
			System.out.print(a + " ");
			printAnnee(a, results, noms);
			System.out.println("");
		}

		System.out.println(sb);
	}

	private static void printAnnee(Integer a, List<Result> results, List<String> noms) {

		for (String nom : noms) {
			printNom(a, nom, results);
		}
	}

	private static void printNom(Integer a, String nom, List<Result> results) {

		for (Result r : results) {
			if (r.getAnnee() == a && r.getNom().equals(nom)) {
				System.out.print(r.getRang() + " ");
			}
		}
	}
}

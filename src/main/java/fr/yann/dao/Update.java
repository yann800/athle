package fr.yann.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

		// SELECT  GROUP_CONCAT( code SEPARATOR ',' ) FROM pays
		String tabCodes[] = { "AFG", "ALB", "ALG", "AND", "ANG", "ANT", "ARG", "ARM", "ARU", "ASA", "AUS", "AUT", "AZE", "BAH", "BAN", "BAR", "BDI", "BEL", "BEN", "BER", "BHU",
				"BIH", "BIZ", "BLR", "BOL", "BOT", "BRA", "BRN", "BRU", "BUL", "BUR", "CAF", "CAM", "CAN", "CAY", "CGO", "CHA", "CHI", "CHN", "CIV", "CMR", "COD", "COK", "COL",
				"COM", "CPV", "CRC", "CRO", "CUB", "CYP", "CZE", "DEN", "DJI", "DMA", "DOM", "ECU", "EGY", "ERI", "ESA", "ESP", "EST", "ETH", "FIJ", "FIN", "FRA", "FSM", "GAB",
				"GAM", "GBR", "GBS", "GEO", "GEQ", "GER", "GHA", "GRE", "GRN", "GUA", "GUI", "GUM", "GUY", "HAI", "HKG", "HON", "HUN", "INA", "IND", "IOA", "IRI", "IRL", "IRQ",
				"ISL", "ISR", "ISV", "ITA", "IVB", "JAM", "JOR", "JPN", "KAZ", "KEN", "KGZ", "KIR", "KOR", "KOS", "KSA", "KUW", "LAO", "LAT", "LBA", "LBR", "LCA", "LES", "LIB",
				"LIE", "LTU", "LUX", "MAD", "MAR", "MAS", "MAW", "MDA", "MDV", "MEX", "MGL", "MHL", "MKD", "MLI", "MLT", "MNE", "MON", "MOZ", "MRI", "MTN", "MYA", "NAM", "NCA",
				"NED", "NEP", "NGR", "NIG", "NOR", "NRU", "NZL", "OMA", "PAK", "PAN", "PAR", "PER", "PHI", "PLE", "PLW", "PNG", "POL", "POR", "PRK", "PUR", "QAT", "ROT", "ROU",
				"RSA", "RUS", "RWA", "SAM", "SEN", "SEY", "SIN", "SKN", "SLE", "SLO", "SMR", "SOL", "SOM", "SRB", "SRI", "SSD", "STP", "SUD", "SUI", "SUR", "SVK", "SWE", "SWZ",
				"SYR", "TAN", "TGA", "THA", "TJK", "TKM", "TLS", "TOG", "TPE", "TTO3", "TUN", "TUR", "TUV", "UAE", "UGA", "UKR", "URU", "USA", "UZB", "VAN", "VEN", "VIE", "VIN",
				"YEM", "ZAM", "ZIM" };
		List<String> codes = Arrays.asList(tabCodes);

		try {
			Class.forName(DRIVER_NAME).newInstance();

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

			// SELECT * FROM ligne where prenom like '%(%' and idPays is null;

			for (String code : codes) {
				System.out.print(code + "\t");
				System.out.println(
						con.createStatement().executeUpdate("UPDATE ligne SET idPays = (SELECT id FROM pays WHERE code LIKE '" + code + "') WHERE prenom LIKE '%(" + code + ")%'"));
			}

			con.close();

			// UPDATE ligne SET idPays = (SELECT id FROM pays WHERE code = 'FRA') where  idPays is null;

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}

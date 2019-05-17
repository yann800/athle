package fr.yann.parser.interclub;

import java.util.StringTokenizer;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.parser.interclub.csv.IC;

public class TraiteCsvInterclub {

	/*
		;;;4 X 400m / SEF;;Finale 2;-;DQ;0;F/;43588;;;
		CRANE Sabrina;;;1 500m / SEF;;Finale 1;8;5'33''86;571;CAF/92;43588;;;
	 */
	public static IC getIC(String line, int annee) {

		// relais
		if (line.contains(";;;")) {
			return traiteRelais(clean(line), annee);
		}

		// ligne bidon
		if (!line.contains(";")) {
			return null;
		}

		return parseCsv(clean(line));

	}

	private static String clean(String line) {
		line.replace(";;;", ";");
		line.replace(";;", ";");
		line.replace(";;", ";");
		return line;
	}

	/*
	 * 1                2             3    4   5        6   7      8
	 * CRANE Sabrina;1 500m / SEF;Finale 1;8;5'33''86;571;CAF/92;43588;
	 */
	private static IC parseCsv(String str) {

		IC perf = new IC();

		StringTokenizer st = new StringTokenizer(str, ";");

		int i = 1;
		while (st.hasMoreElements()) {
			String val = (String) st.nextElement();

			if (i == 1) {
				perf.setNom(val);
			}
			if (i == 2) {
				perf.setEpreuve(EpreuveEnum.getEnumFromCode(EpreuveUtil.cleanCodeEpreuve(val)));
			}
			if (i == 2) {
				perf.setPerf(val.replace("''", ".").replace("'", "."));
			}
			if (i == 4) {
				perf.setNaissance(val);
			}
			if (i == 6) {
				perf.setDate(val);
			}
			i++;
		}


		return perf;
	}

	/*
	 * 1                 2       3  
	 * 4 X 400m / SEF;Finale 2;DQ;0;F/;43588;
	 */
	private static IC traiteRelais(String line, int annee) {
		IC relais = new IC();

		relais.setAnnee(annee);

		StringTokenizer st = new StringTokenizer(line, ";");

		int i = 1;
		while (st.hasMoreElements()) {
			String val = (String) st.nextElement();

			if (i == 1) {
				if (val.contains("100")) {
					relais.setEpreuve(EpreuveEnum.RELAIS_4x100);
				} else {
					relais.setEpreuve(EpreuveEnum.RELAIS_4x400);
				}
			}
			if (i == 3) {
				relais.setPerf(val.replace("''", ".").replace("'", "."));
			}
			i++;
		}


		return relais;
	}

}

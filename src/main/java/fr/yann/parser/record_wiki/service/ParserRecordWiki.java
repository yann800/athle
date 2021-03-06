package fr.yann.parser.record_wiki.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.record.LigneRecordWiki;

public class ParserRecordWiki {

	private static final String CHAR_JOKER = "_";

	public static void main(String[] args) {
		// String str = "3 septembre 1999";
		// System.out.println(getAnneeFromDate(str));

		// System.out.println(isDate("01/01/2013"));
		// System.out.println(isDate("2013/01/01"));
		// System.out.println(isPerf("10.01 er"));
		// System.out.println(isPerf("Bolt Usain 454"));

		// System.out.println(cleanPerf("10.13 (+2,8 m/s)", "100"));

	}

	private static String replaceHtmlTagByJoker(final String str) {

		// supression des balises html
		String patternCleanHtml = "<[^>]*>";
		Pattern p1 = Pattern.compile(patternCleanHtml);
		Matcher m1 = p1.matcher(str);
		String strPlainText = m1.replaceAll(CHAR_JOKER);

		// supression des (RP)
		String patternCleanRP = "[\\s]*\\(RP\\)[\\s]*";
		Pattern p2 = Pattern.compile(patternCleanRP);
		Matcher m2 = p2.matcher(strPlainText);
		String strPlainTextWithoutRP = m2.replaceAll(CHAR_JOKER);

		strPlainTextWithoutRP = strPlainTextWithoutRP.replaceAll("&nbsp;", "");

		return strPlainTextWithoutRP;

	}

	// 1_2_3_4__5_6_7_8_9
	private static String parseStringWithJoker(String line) {

		final String PATTERN = "[_]*([^_]+)";
		final int NB_COL_HTML = 1;

		StringBuffer sbPattern = new StringBuffer();

		for (int i = 0; i < NB_COL_HTML; i++) {
			sbPattern.append(PATTERN);
		}

		// Create a Pattern object
		Pattern r = Pattern.compile(sbPattern.toString());

		// Now create matcher object.
		Matcher m = r.matcher(line);

		if (m.find()) {
			return (m.group(0));
		}
		System.err.println(line);
		return null;
	}

	private static int getAnneeFromDate(String date) {

		final String PATTERN = "([\\d]{4})";

		// Create a Pattern object
		Pattern r = Pattern.compile(PATTERN);

		// Now create matcher object.
		Matcher m = r.matcher(date);

		String strAnnee = null;
		if (m.find()) {
			strAnnee = m.group(0);
		}

		try {
			return Integer.parseInt(strAnnee);
		} catch (@SuppressWarnings("unused") Exception e) {
			// System.err.println(date + " n'est pas une date " + e.getMessage());
			return -1;
		}
	}

	// on traite les td : [epreuve, perf, nom, date]
	// Comme l'ordre n'est pas toujours le même, on reconnait la colonne en analysant la valeur
	public static void traiteLine(String line, LigneRecordWiki lr) {

		if (line.contains("Discipline")
				|| line.contains("Performance")
				|| line.contains("Athlète")
				|| line.contains("Compétition")
				|| line.contains("Lieu")
				|| line.contains("Date")
				|| line.contains("<td></td>")) {
			return;
		}

		String plainText = replaceHtmlTagByJoker(line);

		// String lineWithoutNL = replace(plainText, "NL la veille de la compétition", "null");
		// String lineWithoutCF = replace(plainText, " (F)", "");

		String valeurTd = parseStringWithJoker(plainText);
		if (valeurTd == null) {
			return;
		}
		valeurTd = valeurTd.replace("__", "").replace("_", "");

		if (lr.getEpreuve() == null && isEpreuve(valeurTd)) {
			lr.setEpreuve(cleanEpreuve(valeurTd));
			return;
		}

		if (lr.getPerf() == null && isPerf(valeurTd)) {
			lr.setPerf(valeurTd);
			return;
		}
		if (lr.getNom() == null && isNom(valeurTd)) {
			lr.setNom(valeurTd.replace("'", " "));
			return;
		}
		if (lr.getAnnee() == 0 && isDate(valeurTd)) {
			if (getAnneeFromDate(valeurTd) != -1) {
				lr.setAnnee(getAnneeFromDate(valeurTd));
			}
		}
	}

	private static EpreuveEnum cleanEpreuve(String str) {
		if (str.matches("\\d+.*")) {
			str = str.replace("&#160;", "");

			if (str.contains("steeple") || str.contains("steeple")) {
				str = str.replace("m", "");
			} else {
				str = str.replace(" ", "").replace("m", "");
			}
		}

		str = str.replace(" 000", "000").replace("ètres", "").replace("kilo", "");
		str = str.replace(".", "").replace(",", "").replace("  ", " ").replace(" 000", "000").replace("ètres", "").replace("kilo", "");
		str = str.replace("hurdles", "H");

		
		
		EpreuveEnum e = null;
		
		e = EpreuveEnum.getEnumFromCode(str);
		
		if (e == null){
			System.err.println("not epreuve : " + str);
		}
		
		return e;
	}

	private static boolean isNom(String str) {
		boolean bContient3Caracteres = str.matches(".*[a-zA-Z]{4}.*");
		return bContient3Caracteres;
	}

	private static boolean isDate(String str) {
		boolean bContient4Chiffres = str.matches(".*[0-9]{4}.*");
		return bContient4Chiffres;
	}

	private static boolean isPerf(String str) {

		if (str.matches(".*[a-zA-Z]{4}.*")) {
			return false;
		}

		boolean bContient2Chiffres = str.matches(".*[0-9]{2}.*");
		return bContient2Chiffres;
	}

	private static boolean isEpreuve(String str) {
		if (str.contains("y")) {
			return false;
		}
		
		str = str.toLowerCase();
		
		if (str.startsWith("100") || str.startsWith("200") || str.startsWith("400")
				|| str.startsWith("800") || str.startsWith("1500") || str.startsWith("3000")
				|| str.startsWith("1 500") || str.startsWith("3 000")
				|| str.startsWith("1500") || str.startsWith("3000")
				|| str.startsWith("50")
				|| (str.contains("10") && str.contains("000")) // cas 10[separator]000
				|| (str.contains("relay") && (str.contains("100") || str.contains("400")))

				|| str.startsWith("marathon")

				|| str.startsWith("100 m hurdles") || str.startsWith("110 m hurdles")
				|| str.startsWith("400 m hurdles")
				|| str.startsWith("3000 m steeplechase")

				|| str.startsWith("high jump")
				|| str.startsWith("pole vault")
				|| str.startsWith("long jump")
				|| str.startsWith("triple jump")
				
				|| str.startsWith("shot put")
				|| str.startsWith("discus throw")
				|| str.startsWith("hammer throw")
				|| str.startsWith("javelin throw")
				
				|| str.startsWith("decathlon")
				|| str.startsWith("heptathlon")) {
			return true;
		}
		return false;
	}
}

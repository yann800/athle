package fr.yann.parser.record_wiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.record.LigneRecordWiki;

public class ParserRecordWiki {

	private static final String CHAR_JOKER = "_";

	public static void main(String[] args) {
		String str = "3 septembre 1999";

		System.out.println(getAnneeFromDate(str));

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
		} catch (Exception e) {
			// System.err.println(date + " n'est pas une date " + e.getMessage());
			return -1;
		}
	}

	// on traite les td : epreuve, perf, nom, date
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

		if (lr.getEpreuve() == null) {
			if (valeurTd.matches("\\d+.*")) {
				valeurTd = valeurTd.replace(" ", "").replace("m", "").replace("&#160;", "");
			}

			lr.setEpreuve(valeurTd.replace(".", ""));
			return;
		}

		if (lr.getPerf() == null) {
			lr.setPerf(valeurTd);
			return;
		}
		if (lr.getNom() == null) {
			lr.setNom(valeurTd);
			return;
		}
		if (lr.getAnnee() == 0) {
			if (getAnneeFromDate(valeurTd) != -1) {
				lr.setAnnee(getAnneeFromDate(valeurTd));
			}
		}
	}

}

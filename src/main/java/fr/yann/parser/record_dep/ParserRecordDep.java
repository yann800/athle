package fr.yann.parser.record_dep;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.record.LigneRecordDep;

public class ParserRecordDep {

	private static final String CHAR_JOKER = "_";

	public static void main(String[] args) {
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

		strPlainTextWithoutRP.replace("___", "_");
		strPlainTextWithoutRP.replace("__", "_");
		strPlainTextWithoutRP.replace("__", "_");
		strPlainTextWithoutRP.replace("__", "_");

		return strPlainTextWithoutRP;

	}

	public static void traiteLine(String line, LigneRecordDep lr) {

		// html -->  "__100m____12''1________PIGUET F.____58____Ea bourg____19/05/76____Lyon__"
		String plainText = replaceHtmlTagByJoker(line);

		// System.err.println(plainText);

		StringTokenizer st = new StringTokenizer(plainText, "_");

		int i = 1;
		while (st.hasMoreElements()) {
			String val = (String) st.nextElement();

			if (i == 1) {
				lr.setEpreuve(cleanEpreuve(val));
			}
			if (i == 2) {
				lr.setPerf(val.replace("''", ".").replace("'", "."));
			}
			if (i == 3) {
				lr.setNom(val);
			}
			if (i == 4) {
				lr.setNaissance(val);
			}
			if (i == 5) {
				lr.setClub(val);
			}
			if (i == 6) {
				lr.setDate(val);
			}
			if (i == 7) {
				lr.setLieu(val.replace("'", " "));
			}
			i++;
		}

	}

	private static EpreuveEnum cleanEpreuve(String str) {
		if (str.matches("\\d+.*")) {
			str = str.replace("&#160;", "");

			if (str.contains("Steeple") || str.contains("steeple")) {
				str = str.replace("m", "");
			} else {
				str = str.replace(" ", "").replace("m", "");
			}
		}

		str = str.replace(" 000", "000").replace("ètres", "").replace("kilo", "");
		str = str.replace(".", "").replace(",", "").replace("  ", " ").replace(" 000", "000").replace("ètres", "").replace("kilo", "");
		str = str.replace("Haies(76)", "H");

		
		
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

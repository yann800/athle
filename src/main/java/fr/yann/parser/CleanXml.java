package fr.yann.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanXml {

	private static final String CHAR_JOKER = "_";

	public static void main(String[] args) {
		
		String line = "<tr><td class=\"datas0\">1071</td><td class=\"separator3\"></td><td class=\"datas0\"><i><b>41'47''</b></i><span title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas0\">M</td><td class=\"separator3\"></td><td class=\"datas0\"><i>ANGLE Florentine</i></td><td class=\"separator3\"></td><td class=\"datas0\">null</td><td class=\"separator3\"></td><td class=\"datas0\"><i><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=261&amp;frmsexe=F&amp;frmligue=\"\"></a></i></td><td class=\"separator3\"></td><td class=\"datas0\"><i><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=261&amp;frmsexe=F&amp;frmdepartement=\"></a></i></td><td class=\"separator3\"></td><td class=\"datas0\"><i>VEF</i></td><td class=\"separator3\"></td><td class=\"datas0\"><i><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=261&amp;frmsexe=F&amp;frmamini=1957&amp;frmamaxi=1957\">57</a></i></td><td class=\"separator3\"></td><td class=\"datas0\">12/03/06</td><td class=\"separator3\"></td><td class=\"datas0\">Villeurbanne</td></tr>";

		System.out.println(traiteLineAtypique(line, 10, 0).toString());

	}

	private static String replace(String line, String oldStr, String newStr) {
		return line.replace(oldStr, newStr);
	}

	private static String replaceHtmlTagByJoker(final String str) {
		
		// supression des balises html
		String patternCleanHtml = "<[^>]*>";
		Pattern p1 = Pattern.compile(patternCleanHtml);
		Matcher m1 = p1.matcher(str);
		String strPlainText = m1.replaceAll(CHAR_JOKER);

		// supression des balises html
		String patternCleanRP = "[\\s]*\\(RP\\)[\\s]*";
		Pattern p2 = Pattern.compile(patternCleanRP);
		Matcher m2 = p2.matcher(strPlainText);
		String strPlainTextWithoutRP = m2.replaceAll(CHAR_JOKER);

		return strPlainTextWithoutRP;

	}

	// 1_2_3_4__5_6_7_8_9
	private static LigneBilan parseStringWithJoker(String line, int idEpreuve, int sexe) {

		final String PATTERN = "[_]*([^_]+)";
		final int NB_COL_HTML = 11;

		StringBuffer sbPattern = new StringBuffer();

		for (int i = 0; i < NB_COL_HTML; i++) {
			sbPattern.append(PATTERN);
		}

		// Create a Pattern object
		Pattern r = Pattern.compile(sbPattern.toString());

		// Now create matcher object.
		Matcher m = r.matcher(line);

		LigneBilan lb = null;

		if (m.find()) {

			// for (int i = 1; i < 12; i++) {				System.out.println(i + " " + m.group(i));			}

			NomPrenom nomPrenom = new NomPrenom(m.group(4));

			lb = new LigneBilan(
					Integer.parseInt(m.group(1)), // rang
					m.group(2).replace("''", ".").replace("'", "."), // perf
					nomPrenom.nom,
					nomPrenom.prenom,
					m.group(5).replace("'", " "), // club
					m.group(6), // ligue
					Integer.parseInt(m.group(7)), // anneeNaissance
					// m.group(8), // categorie
					m.group(10), // datePerf
					m.group(11).replace("'", " "), //  + m.group(12).replace("'", " ") + m.group(13).replace("'", " "), // ville
					idEpreuve,
					sexe);
		}
		else {
			System.err.println(line);
		}
		return lb;
	}

	public static LigneBilan traiteLineAtypique(String line, int idEpreuve, int sexe) {

		if (line.contains("NL la veille de")) {
			return null;
		}

		String plainText = replaceHtmlTagByJoker(line);

		// String lineWithoutNL = replace(plainText, "NL la veille de la compétition", "null");
		String lineWithoutCF = replace(plainText, " (F)", "");


		return parseStringWithJoker(lineWithoutCF, 10, 0);
	}

}

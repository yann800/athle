package fr.yann.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.NomPrenom;
import fr.yann.model.bilan.LastRang;
import fr.yann.model.bilan.LigneBilan;
import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class CleanXml {

	private static final String CHAR_JOKER = "_";

	public static void main(String[] args) {
		
		// String line = "<tr><td class=\"datas0\">1071</td><td class=\"separator3\"></td><td class=\"datas0\"><i><b>41'47''</b></i><span title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas0\">M</td><td class=\"separator3\"></td><td class=\"datas0\"><i>ANGLE Florentine</i></td><td class=\"separator3\"></td><td class=\"datas0\">null</td><td class=\"separator3\"></td><td class=\"datas0\"><i><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=261&amp;frmsexe=F&amp;frmligue=\"\"></a></i></td><td class=\"separator3\"></td><td class=\"datas0\"><i><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=261&amp;frmsexe=F&amp;frmdepartement=\"></a></i></td><td class=\"separator3\"></td><td class=\"datas0\"><i>VEF</i></td><td class=\"separator3\"></td><td class=\"datas0\"><i><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=261&amp;frmsexe=F&amp;frmamini=1957&amp;frmamaxi=1957\">57</a></i></td><td class=\"separator3\"></td><td class=\"datas0\">12/03/06</td><td class=\"separator3\"></td><td class=\"datas0\">Villeurbanne</td></tr>";
		String line = "<tr><td class=\"datas1\">810</td><td class=\"separator3\"></td><td class=\"datas1\"><b>52''31</b><span style=\"font-weight:bold;padding-left:3px\" title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas1\">E</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"javascript:bddThrowAthlete('bilans', 96306, 0)\" title=\"cliquez pour le détail\">PIVAUT Thomas</a></td><td class=\"separator3\"></td><td class=\"datas1\">Esc ocean 44 saint nazaire *</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmligue=P-L\">P-L</a></td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmdepartement=044\">044</a></td><td class=\"separator3\"></td><td class=\"datas1\">JUM</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmamini=1985&frmamaxi=1985\">85</a></td><td class=\"separator3\"></td><td class=\"datas1\">28/05/04</td><td class=\"separator3\"></td><td class=\"datas1\">La chapelle / e</td></tr>";
		LastRang lastRang = new LastRang();
		lastRang.setRang(1);

		System.out.println(traiteLineAtypique(line, EpreuveEnum.COURSE_10_KM, SexeEnum.MASCULIN, lastRang));

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
	private static LigneBilan parseStringWithJoker(String line, EpreuveEnum epreuveEnum, SexeEnum sexeEnum, LastRang lastRang) {

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

			String mGroup1 = m.group(1); // rang;
			if (mGroup1.equals("-")){
				// System.out.println(lastRang.getRang());
			} else {
				lastRang.setRang(Integer.parseInt(mGroup1));
			}

			NomPrenom nomPrenom = new NomPrenom(m.group(4));

			String anneNaissance = m.group(9);
			
			if (anneNaissance.length() != 2){
				System.out.println("ERREUR anneNaissance " + anneNaissance);
				return null;
			}
			
			lb = new LigneBilan(
					lastRang.getRang(), // rang
					m.group(2).replace("''", ".").replace("'", "."), // perf
					nomPrenom.nom,
					nomPrenom.prenom,
					m.group(5).replace("'", " "), // club
					m.group(6), // ligue
					Integer.parseInt(anneNaissance), // anneeNaissance
					// m.group(8), // categorie
					m.group(10), // datePerf
					m.group(11).replace("'", " "), //  + m.group(12).replace("'", " ") + m.group(13).replace("'", " "), // ville
					epreuveEnum.code,
					sexeEnum.getCodeInt());
		}
		else {
			System.err.println(line);
		}
		return lb;
	}

	public static LigneBilan traiteLineAtypique(String line, EpreuveEnum epreuveEnum, SexeEnum sexeEnum, LastRang lastRang) {

		if (line.contains("NL la veille de")) {
			return null;
		}

		String plainText = replaceHtmlTagByJoker(line);

		// String lineWithoutNL = replace(plainText, "NL la veille de la compétition", "null");
		String lineWithoutCF = replace(plainText, " (F)", "");

		System.out.println(lineWithoutCF);

		return parseStringWithJoker(lineWithoutCF, epreuveEnum, sexeEnum, lastRang);
	}

}

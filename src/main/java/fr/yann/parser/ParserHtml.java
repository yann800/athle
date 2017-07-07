package fr.yann.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.bilan.LastRang;
import fr.yann.model.bilan.LigneBilan;
import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

/**
 * 
 * Parsing du HTML des pages bilans. Les lignes qui nous intéressent commencent par :
 * <tr><td class="datas0">
 * <tr><td class="datas1">
 */
public class ParserHtml {

	public static void main(String[] args) {

		// ex. ligne 10km String line = "<tr><td class=\"datas0\">1343</td><td class=\"separator3\"></td><td class=\"datas0\"><b>33'32''</b><span title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas0\">M</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"javascript:bddThrowAthlete('bilans',%201226306,%200)\" title=\"cliquez pour le détail\">ABDELKADER Yann</a></td><td class=\"separator3\"></td><td class=\"datas0\">Ac paris-joinville</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2015&amp;frmepreuve=261&amp;frmsexe=M&amp;frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2015&amp;frmepreuve=261&amp;frmsexe=M&amp;frmdepartement=094\">094</a></td><td class=\"separator3\"></td><td class=\"datas0\">SEM</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2015&amp;frmepreuve=261&amp;frmsexe=M&amp;frmamini=1977&amp;frmamaxi=1977\">77</a></td><td class=\"separator3\"></td><td class=\"datas0\">27/12/15</td><td class=\"separator3\"></td><td class=\"datas0\">Houilles</td></tr>";
		// String line1 = "<tr><td class=\"datas0\">1</td><td class=\"separator3\"></td><td class=\"datas0\"><b>44''64</b></td><td class=\"separator3\"></td><td class=\"datas0\">E</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"javascript:bddThrowAthlete('bilans', 117644, 0)\" title=\"cliquez pour le dÃ©tail\">DJHONE Leslie</a></td><td class=\"separator3\"></td><td class=\"datas0\">Neuilly-plaisance sports</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmdepartement=093\">093</a></td><td class=\"separator3\"></td><td class=\"datas0\">SEM</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmamini=1981&frmamaxi=1981\">81</a></td><td class=\"separator3\"></td><td class=\"datas0\">08/08/04</td><td class=\"separator3\"></td><td class=\"datas0\">La chaux-de-fon (SUI)</td></tr>";
		// String line2 = "<tr><td class=\"datas1\">2</td><td class=\"separator3\"></td><td class=\"datas1\"><b>45''74</b><span style=\"font-weight:bold;padding-left:3px\" title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas1\">E</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"javascript:bddThrowAthlete('bilans', 194228, 0)\" title=\"cliquez pour le dÃ©tail\">KEITA Naman</a></td><td class=\"separator3\"></td><td class=\"datas1\">Avia club issy-les-moulx</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmdepartement=092\">092</a></td><td class=\"separator3\"></td><td class=\"datas1\">SEM</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmamini=1978&frmamaxi=1978\">78</a></td><td class=\"separator3\"></td><td class=\"datas1\">03/04/04</td><td class=\"separator3\"></td><td class=\"datas1\">Dakar (SEN)</td></tr>";
		// String line3 = "<tr><td class=\"datas1\">3</td><td class=\"separator3\"></td><td class=\"datas1\"><b>45''76</b><span style=\"font-weight:bold;padding-left:3px\" title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas1\">E</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"javascript:bddThrowAthlete('bilans', 99756, 0)\" title=\"cliquez pour le dÃ©tail\">MAUNIER Richard</a></td><td class=\"separator3\"></td><td class=\"datas1\">Racing cf (paris)</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmdepartement=075\">075</a></td><td class=\"separator3\"></td><td class=\"datas1\">SEM</td><td class=\"separator3\"></td><td class=\"datas1\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmamini=1977&frmamaxi=1977\">77</a></td><td class=\"separator3\"></td><td class=\"datas1\">08/08/04</td><td class=\"separator3\"></td><td class=\"datas1\">La chaux-de-fon (SUI)</td></tr>";
		// String line4 = "<tr><td class=\"datas0\">-</td><td class=\"separator3\"></td><td class=\"datas0\"><b>45''76</b></td><td class=\"separator3\"></td><td class=\"datas0\">E</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"javascript:bddThrowAthlete('bilans', 194230, 0)\" title=\"cliquez pour le dÃ©tail\">WADE Ibrahima</a></td><td class=\"separator3\"></td><td class=\"datas0\">Villemomble sports</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmdepartement=093\">093</a></td><td class=\"separator3\"></td><td class=\"datas0\">SEM</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmamini=1968&frmamaxi=1968\">68</a></td><td class=\"separator3\"></td><td class=\"datas0\">23/07/04</td><td class=\"separator3\"></td><td class=\"datas0\">St-denis</td></tr>";

		String line3 = "";

		StringBuffer sb = new StringBuffer();

		LastRang lastRang = new LastRang();
		lastRang.setRang(1);
		// traiteLigne(line1, sb, EpreuveEnum.COURSE_400, SexeEnum.MASCULIN);
		// traiteLigne(line2, sb, EpreuveEnum.COURSE_400, SexeEnum.MASCULIN);
		traiteLigne(line3, sb, EpreuveEnum.COURSE_400, SexeEnum.MASCULIN, lastRang, 2003);
		// traiteLigne(line4, sb, EpreuveEnum.COURSE_400, SexeEnum.MASCULIN);
		
		System.out.println(sb);
	}

	/**
	 * Ecrit dans un fichier SQL les INSERT. toto.html > toto.sql
	 * @param path D:\Users\yabd-el-kader\Desktop\athle\bilan\liste_000.html 
	 * @param epreuveEnum 
	 * @param sexeEnum 
	 * @param lastRang 
	 * @param annee 
	 * @throws IOException 
	 */
	public static void ecrireSqlInsertValues(String path, EpreuveEnum epreuveEnum, SexeEnum sexeEnum, LastRang lastRang, int annee) throws IOException {

		// System.out.println("Lecture de " + path);

		// ApplicationContext appContext = new ClassPathXmlApplicationContext("spring_parser.xml");

		// LigneService ligneService = null; // (LigneService) appContext.getBean("ligneService");

		
		// prepare writer
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ligne (rang, perf, nom, prenom, sexe, club, ligue, datePerf, naissance, ville, idEpreuve, annee) VALUES ");

		// rempli le sb
		lireFichier(path, sb, epreuveEnum, sexeEnum, lastRang, annee);

		String pathSql = path.replace(".html", ".sql");

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathSql), "utf-8"))) {
			writer.write(sb.toString().substring(0, sb.toString().length()-1));
			writer.write(";");
		} catch (IOException ex) {
			throw ex;
		}
		
	}
	
	/**
	 * rempli le sb donné en paramètre
	 * @param annee 
	 * @param currentRang 
	 */
	private static void lireFichier(String path, StringBuffer sb, EpreuveEnum epreuveEnum, SexeEnum sexeEnum, LastRang lastRang, int annee) throws FileNotFoundException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		try {
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				traiteLigne(line, sb, epreuveEnum, sexeEnum, lastRang, annee);
			}
			
			br.close();
			fr.close();
		} catch (IOException exception) {
			System.err.println("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

	/**
	 * Ex : <tr><td class="datas0">1</td><td class="separator3"></td><td class="datas0"><b>1'44''04</b></td><td class="separator3"></td><td class="datas0">E</td><td class="separator3"></td><td class="datas0"><a href="javascript:bddThrowAthlete('bilans',%20127580,%200)" title="cliquez pour le détail">BAALA Mehdi</a></td><td class="separator3"></td><td class="datas0">Asptt strasbourg*</td><td class="separator3"></td><td class="datas0"><a href="http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=208&amp;frmsexe=M&amp;frmligue=ALS">ALS</a></td><td class="separator3"></td><td class="datas0"><a href="http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=208&amp;frmsexe=M&amp;frmdepartement=067">067</a></td><td class="separator3"></td><td class="datas0">SEM</td><td class="separator3"></td><td class="datas0"><a href="http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=208&amp;frmsexe=M&amp;frmamini=1978&amp;frmamaxi=1978">78</a></td><td class="separator3"></td><td class="datas0">18/08/06</td><td class="separator3"></td><td class="datas0">Zurich (SUI)</td></tr>
	 * @param line
	 * @param sb 
	 * @param isLastLine 
	 */
	private static void traiteLigne(String line, StringBuffer sb, EpreuveEnum epreuveEnum, SexeEnum sexeEnum, LastRang lastRang, int annee) {

		if (line == null || !line.startsWith("<tr><td class=\"datas")) {
			return;
		}

		// String to be scanned to find the pattern.

		// non échappé  : <tr><[^>]*>([^<]*)[^b]*><b>([^<]*)[^é]*étail">(\p{Lu}*)\s(\p{Alpha}*)</a></td><td class="separator3"></td><td class="datas[01]">([^<]*)</td><td class="separator3"></td><td class="datas[01]">[^>]*>([\p{Alpha}-]*)</a></td><td[^<]*</td><td[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<([^<]*)
		//                <tr><[^>]*>([^<]*)[^b]*><b>([^<]*)[^é]*étail\">([\p{Lu}\s-]*)\s([\p{Alpha}\s-\(\)]*)</a></td><td class=\"separator3\"></td><td class=\"datas[01]\">([^<]*)</td><td class=\"separator3\"></td><td class=\"datas[01]\">[^>]*>([\p{Alpha}-]*)</a></td><td[^<]*</td><td[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<td\sclass="datas[01]">([^<]*)<[^<]*<[^<]*<[^<]*<[^>]*>([^<]*)
		
		
		// good sans parenthese String pattern = "<tr><[^>]*>([^<]*)[^b]*>([^<]*)<b>[^é]*étail\">([\\p{Lu}\\s-]*)\\s([\\p{Alpha}\\s-]*)</a></td><td class=\"separator3\"></td><td class=\"datas[01]\">([^<]*)</td><td class=\"separator3\"></td><td class=\"datas[01]\">[^>]*>([\\p{Alpha}-]*)</a></td><td[^<]*</td><td[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^>]*>([^<]*)";

		String pattern = "<tr><[^>]*>([^<]*)[^b]*><b>([^<]*)[^é]*étail\">([\\p{Lu}\\s-]*)\\s([\\p{Alpha}\\s-\\(\\)]*)</a></td><td class=\"separator3\"></td><td class=\"datas[01]\">([^<]*)</td><td class=\"separator3\"></td><td class=\"datas[01]\">[^>]*>([\\p{Alpha}-]*)</a></td><td[^<]*</td><td[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^<]*<[^>]*>([^<]*)<[^<]*<[^<]*<[^<]*<[^<]*<td\\sclass=\"datas[01]\">([^<]*)<[^<]*<[^<]*<[^<]*<[^>]*>([^<]*)";
		
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		
		// System.out.println(line);
		
		if (m.find()) {

			String mGroup1 = m.group(1); // rang;
			if (mGroup1.equals("-")){
				// System.out.println(lastRang.getRang());
			} else {
				lastRang.setRang(Integer.parseInt(mGroup1));
			}
			String prenom = m.group(4).replace("'", " ");
			String pays = "";

			if (prenom.contains("(")) {
				int indexParenthese = prenom.indexOf("(");
				pays = prenom.substring(indexParenthese);
				System.out.println(pays);
				prenom = prenom.substring(0, indexParenthese - 1);
			}

			LigneBilan lb = new LigneBilan(
					lastRang.getRang(),
					m.group(2).replace("''", ".").replace("'", "."), // perf
					m.group(3).replace("'", " "), // nom
					prenom, // prenom
					m.group(5).replace("'", " "), // club
					m.group(6), // ligue
					Integer.parseInt(m.group(7)), // anneeNaissance
					m.group(8), // datePerf
					m.group(9).replace("'", " "),  // ville
					epreuveEnum.getCode(),
					sexeEnum.getCodeInt(),
					pays, annee);


			sb.append(lb);
		} else {
			LigneBilan lb;

			if (annee > 2003) {
				lb = CleanXml.traiteLineAtypique(line, epreuveEnum, sexeEnum, lastRang, annee);
			} else {
				lb = CleanXmlBefore2004.traiteLineAtypique(line, epreuveEnum, sexeEnum, lastRang, annee);
			}
			if (lb != null) {
				// System.out.println(lb.toStringInsert());
				sb.append(lb.toString());
			}
		}
	}

}

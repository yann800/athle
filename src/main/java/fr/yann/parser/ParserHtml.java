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

/**
 * 
 * Parsing du HTML des pages bilans. Les lignes qui nous intéressent commencent par :
 * <tr><td class="datas0">
 * <tr><td class="datas1">
 */
public class ParserHtml {

	/**
	 * arg : D:\Users\yabd-el-kader\Desktop\athle\bilan\liste_000.html
	 * @param path 
	 * @param idEpreuve 
	 * @param sexe 
	 * @throws IOException 
	 */
	public static void main(String path, int idEpreuve, int sexe) throws IOException {

		// System.out.println("Lecture de " + path);

		// ApplicationContext appContext = new ClassPathXmlApplicationContext("spring_parser.xml");

		// LigneService ligneService = null; // (LigneService) appContext.getBean("ligneService");

		
		// prepare writer
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO `ligne` (`rang`, `perf`, `nom`, `prenom`, `sexe`, `club`, `ligue`, `datePerf`, `naissance`, `ville`, `idEpreuve`) VALUES ");

		lireFichier(path, sb, idEpreuve, sexe);

		String pathSql = path.replace(".html", ".sql");

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathSql), "utf-8"))) {
			writer.write(sb.toString().substring(0, sb.toString().length()-1));
			writer.write(";");
		} catch (IOException ex) {
			throw ex;
		}
		
	}

	/**
	 * Ex : <tr><td class="datas0">1</td><td class="separator3"></td><td class="datas0"><b>1'44''04</b></td><td class="separator3"></td><td class="datas0">E</td><td class="separator3"></td><td class="datas0"><a href="javascript:bddThrowAthlete('bilans',%20127580,%200)" title="cliquez pour le détail">BAALA Mehdi</a></td><td class="separator3"></td><td class="datas0">Asptt strasbourg*</td><td class="separator3"></td><td class="datas0"><a href="http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=208&amp;frmsexe=M&amp;frmligue=ALS">ALS</a></td><td class="separator3"></td><td class="datas0"><a href="http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=208&amp;frmsexe=M&amp;frmdepartement=067">067</a></td><td class="separator3"></td><td class="datas0">SEM</td><td class="separator3"></td><td class="datas0"><a href="http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2006&amp;frmepreuve=208&amp;frmsexe=M&amp;frmamini=1978&amp;frmamaxi=1978">78</a></td><td class="separator3"></td><td class="datas0">18/08/06</td><td class="separator3"></td><td class="datas0">Zurich (SUI)</td></tr>
	 * @param line
	 * @param sb 
	 * @param isLastLine 
	 */
	private static void traiteLigne(String line, StringBuffer sb, int idEpreuve, int sexe) {

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
		if (m.find()) {

			LigneBilan lb = new LigneBilan(
					Integer.parseInt(m.group(1)), // rang
					m.group(2).replace("''", ".").replace("'", "."), // perf
					m.group(3).replace("'", " "), // nom
					m.group(4).replace("'", " "), // prenom
					m.group(5).replace("'", " "), // club
					m.group(6), // ligue
					Integer.parseInt(m.group(7)), // anneeNaissance
					m.group(8), // datePerf
					m.group(9).replace("'", " "),  // ville
					idEpreuve,
					sexe);


			sb.append(lb);
		} else {
			LigneBilan lb = CleanXml.traiteLineAtypique(line, idEpreuve, sexe);
			if (lb != null) {
				// System.out.println(lb.toStringInsert());
				sb.append(lb);
			}
		}

	}

	private static void lireFichier(String path, StringBuffer sb, int idEpreuve, int sexe) throws FileNotFoundException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		try {
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				traiteLigne(line, sb, idEpreuve, sexe);
			}

			br.close();
			fr.close();
		} catch (IOException exception) {
			System.err.println("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

	public static void main(String[] args) {

		// String line = "<tr><td class=\"datas0\">1343</td><td class=\"separator3\"></td><td class=\"datas0\"><b>33'32''</b><span title=\"Record personnel tous temps\"> (RP)</span></td><td class=\"separator3\"></td><td class=\"datas0\">M</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"javascript:bddThrowAthlete('bilans',%201226306,%200)\" title=\"cliquez pour le détail\">ABDELKADER Yann</a></td><td class=\"separator3\"></td><td class=\"datas0\">Ac paris-joinville</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2015&amp;frmepreuve=261&amp;frmsexe=M&amp;frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2015&amp;frmepreuve=261&amp;frmsexe=M&amp;frmdepartement=094\">094</a></td><td class=\"separator3\"></td><td class=\"datas0\">SEM</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"http://bases.athle.com/asp.net/liste.aspx?frmbase=bilans&amp;frmmode=1&amp;frmannee=2015&amp;frmepreuve=261&amp;frmsexe=M&amp;frmamini=1977&amp;frmamaxi=1977\">77</a></td><td class=\"separator3\"></td><td class=\"datas0\">27/12/15</td><td class=\"separator3\"></td><td class=\"datas0\">Houilles</td></tr>";
		String line = "<tr><td class=\"datas0\">1</td><td class=\"separator3\"></td><td class=\"datas0\"><b>44''64</b></td><td class=\"separator3\"></td><td class=\"datas0\">E</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"javascript:bddThrowAthlete('bilans', 117644, 0)\" title=\"cliquez pour le dÃ©tail\">DJHONE Leslie</a></td><td class=\"separator3\"></td><td class=\"datas0\">Neuilly-plaisance sports</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmligue=I-F\">I-F</a></td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmdepartement=093\">093</a></td><td class=\"separator3\"></td><td class=\"datas0\">SEM</td><td class=\"separator3\"></td><td class=\"datas0\"><a href=\"/asp.net/liste.aspx?frmbase=bilans&frmmode=1&frmannee=2004&frmepreuve=140&frmsexe=M&frmamini=1981&frmamaxi=1981\">81</a></td><td class=\"separator3\"></td><td class=\"datas0\">08/08/04</td><td class=\"separator3\"></td><td class=\"datas0\">La chaux-de-fon (SUI)</td></tr>";
		StringBuffer sb = new StringBuffer();
		traiteLigne(line, sb, 10, 0);
		System.out.println(sb);
	}
}

package fr.yann.parser.record;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.enums.SexeEnum;
import fr.yann.model.record.LigneRecord;
import fr.yann.model.record.Records;

/**
 * 
 * Parsing du texte simple issu du copier coller de HTML tel que
100m		11''38 (+0.8)		 		LOVAL Marie-france		64		Aa pointre-à-pitre		20/08/81		Utrecht
 */
public class ParserSimpleTxtFromTabularRecord {

	static SexeEnum			SEXE_COURANT	= SexeEnum.FEMININ;
	static String			sexeStr			= "Femme";

	static String			epreuve			= "([\\d]+[\\w]+)[\\s]{1,4}";
	static String			perf			= "(\\d{0,2}\\D{0,2}\\d{1,2})[\\s]{1,4}";
	static String			nom				= "([^\\d]+)";
	// static String			age				= "(\\d\\d)\\t";
	// static String 		strDate = "(\\d{2}/\\d{2}/\\d{2})\\s";
	static String			pattern			= epreuve + perf + nom;							// + age + strDate;
	
	static Pattern r = Pattern.compile(pattern);
	private static String currentEpreuve = "100m";
	private static String currentCategorie = "cadet";

	static Records records = new Records();
	
	public static void main(String[] args) throws FileNotFoundException {

		String path = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record\\recordFranceFemme.txt";

		StringBuffer sb = new StringBuffer();
		

		lireFichier(path, sb, SEXE_COURANT);


		// System.out.println(sb.toString());
		List<String> categories = new ArrayList<String>();
		categories.add(SEXE_COURANT.getCodeStr() + "cadet");
		categories.add(SEXE_COURANT.getCodeStr() + "junior");
		categories.add(SEXE_COURANT.getCodeStr() + "35");
		categories.add(SEXE_COURANT.getCodeStr() + "40");
		categories.add(SEXE_COURANT.getCodeStr() + "45");
		categories.add(SEXE_COURANT.getCodeStr() + "50");
		categories.add(SEXE_COURANT.getCodeStr() + "55");
		categories.add(SEXE_COURANT.getCodeStr() + "60");
		categories.add(SEXE_COURANT.getCodeStr() + "65");
		categories.add(SEXE_COURANT.getCodeStr() + "70");
		categories.add(SEXE_COURANT.getCodeStr() + "75");
		categories.add(SEXE_COURANT.getCodeStr() + "80");
		categories.add(SEXE_COURANT.getCodeStr() + "85");


		for (String cat:categories) {

			if (records.getRecords(cat) == null) {
				System.out.println("ERREUR NULL records.getRecords(" + cat + ")");
				continue;
			}

			String str = "var recordsMonde" + sexeStr + cat + " = {'record' : [";
			for (LigneRecord lb : records.getRecords(cat)) {
				str = str + lb.toStringJson();
			}
			str = str + "]};";
			System.out.println(str);
		}
	}

	private static void lireFichier(String path, StringBuffer sb, SexeEnum sexeEnum) throws FileNotFoundException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		try {
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				traiteLigne(line, sb, sexeEnum);
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
	private static void traiteLigne(String line, StringBuffer sb, SexeEnum sexeEnum) {

		if (line == null) {
			return;
		}

		// supprimer les partenthèses
		// line = line.replaceAll("\\([\\+\\-]\\d\\.\\d\\)", "");

		if (line.contains("cadet")){currentCategorie = "Fcadet";currentEpreuve="100"; return;}
		if (line.contains("cadet")){currentCategorie = "Fjunior";currentEpreuve="100"; return;}
		if (line.contains("F35")){currentCategorie = "F35";currentEpreuve="100"; return;}
		if (line.contains("F40")){currentCategorie = "F40";currentEpreuve="100"; return;}
		
		if (line.contains("200m")){currentEpreuve = "200";return;}
		if (line.contains("400m")){currentEpreuve = "400";return;}
		if (line.contains("800m")){currentEpreuve = "800";return;}
		if (line.contains("1 500m")){currentEpreuve = "1500";return;}
		if (line.contains("3 000m")){currentEpreuve = "3000";return;}
		if (line.contains("5 000m")){currentEpreuve = "5000";return;}
		if (line.contains("10 000m")){currentEpreuve = "10000";return;}
		if (line.contains("Steeple")){currentEpreuve = "3000 st.";return;}
		if (line.contains("400m Haies")){currentEpreuve = "400 Haies";return;}
		if (line.contains("Longueur")){currentEpreuve = "Hauteur";return;}
		if (line.contains("Perche")){currentEpreuve = "Perche";return;}
		if (line.contains("Triple")){currentEpreuve = "Triple";return;}
		
		
		Matcher m = r.matcher(line);
		if (m.find()) {

			LigneRecord lb = new LigneRecord(
					currentCategorie,
					currentEpreuve,
					m.group(2), // perf
					m.group(3), // nom
					0, // Integer.parseInt(m.group(4)), // age
					0, // getAnnee(m.group(5)), // annee
					sexeEnum
					);
			// sb.append(lb.toStringJson() + "\n");
			
			records.add(lb);
		}

	}

	private static int getAnnee(String strDate) {
		int annee;
		try {
			annee = Integer.parseInt(strDate.substring(6, 8));
		} catch (Exception e) {
			return 9999;
		}
		if (annee > 50) {
			return annee + 1900;
		}
		return annee + 2000;
	}
}

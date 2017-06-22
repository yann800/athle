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

import fr.yann.model.SexeEnum;

/**
 * 
 * Parsing du texte simple issu du copier coller de PDF tel que
		M 35 9.96 Kim Collins SKN 38 20/07/14 London, GBR
		M 40 9.93 Kim Collins SKN 40 29/05/16 Bottrop, GER
 */
public class ParserSimpleTxtFromPdfRecord {

	static SexeEnum			SEXE_COURANT	= SexeEnum.FEMININ;
	static String			sexeStr			= "Femme";

	static String			categorie		= "([W]\\s\\d{1,2})\\s";						// M ou W selon sexe
	static String perf = "(\\d{0,2}\\D{0,2}\\d{1,2}\\D\\d{1,2})\\s";
	static String nom = "([^\\d]+)\\s";
	static String age = "(\\d\\d)\\s";
	static String strDate = "(\\d{2}/\\d{2}/\\d{2})\\s";
	static String pattern = categorie + perf + nom + age + strDate;
	
	static Pattern r = Pattern.compile(pattern);
	private static String currentEpreuve = "100m";

	static Records records = new Records();
	
	public static void main(String[] args) throws FileNotFoundException {

		String path = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record\\recordMondeVeteranFemme.txt";

		StringBuffer sb = new StringBuffer();
		

		lireFichier(path, sb, SEXE_COURANT);


		// System.out.println(sb.toString());
		List<String> categories = new ArrayList<String>();
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

		if (line.contains("200m")){currentEpreuve = "200";return;}
		if (line.contains("400m")){currentEpreuve = "400";return;}
		if (line.contains("800m")){
			currentEpreuve = "800";return;}
		if (line.contains("1500m")){currentEpreuve = "1500";return;}
		if (line.contains("3000m")){currentEpreuve = "3000";return;}
		if (line.contains("5000m")){currentEpreuve = "5000";return;}
		if (line.contains("10000m")){currentEpreuve = "10000";return;}
		if (line.contains("Steeplechase")){currentEpreuve = "3000 st.";return;}
		if (line.contains("400m Hurdles")){currentEpreuve = "400 Haies";return;}
		if (line.contains("High Jump")){currentEpreuve = "Hauteur";return;}
		if (line.contains("Pole Vault")){currentEpreuve = "Perche";return;}
		if (line.contains("Longueur")){currentEpreuve = "Longueur";return;}
		if (line.contains("Triple Jump")){currentEpreuve = "Triple";return;}
		
		
		Matcher m = r.matcher(line);
		if (m.find()) {

			LigneRecord lb = new LigneRecord(
					m.group(1).replace(" ", "").replace("W", "F"), // categorie
					currentEpreuve,
					m.group(2), // perf
					m.group(3), // nom
					Integer.parseInt(m.group(4)), // age
					getAnnee(m.group(5)), // annee
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

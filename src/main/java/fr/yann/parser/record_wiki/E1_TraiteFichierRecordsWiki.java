package fr.yann.parser.record_wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;
import fr.yann.model.record.LigneRecordWiki;
import fr.yann.parser.record_wiki.service.GetNomPaysWikiEn;
import fr.yann.parser.record_wiki.service.ParserRecordWiki;
import fr.yann.parser.record_wiki.service.SqlService;

/**
 * 
 * HTML -> SQL
 *
 */
public class E1_TraiteFichierRecordsWiki {

	public  static final String PATH_PAGES_WIKI = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_wiki\\pages";

	// Insert.main(path + ".sql");
	public static void main(String[] args) throws Exception {

		Map<Integer, String> map = GetNomPaysWikiEn.getMap();

		for (Integer num : map.keySet()) {
			
			// if (num != 69) {continue;}
			
			System.out.println();
			StringBuffer sb = new StringBuffer();

			String path = PATH_PAGES_WIKI + "\\pays" + num + ".html";

			List<LigneRecordWiki> liste = traite(path, num);

			// System.out.println("NOMBRE : " + liste.size() + "\n");
			for (LigneRecordWiki lr : liste) {
				if (lr.getPerf() == null) {
					continue;
				}

				System.out.println(lr.toStringSql());
				sb.append(lr.toStringSql() + "\n");
			}
			SqlService.writeFile(path, sb.toString());
		}


	}

	private static List<LigneRecordWiki> traite(final String pathFile, int idPays) throws Exception {

		File f = new File(pathFile);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		// boolean bTrEncours = false;

		SexeEnum sexe = SexeEnum.MASCULIN;
		LigneRecordWiki lr = null;
		List<LigneRecordWiki> listeRecords = new ArrayList<>();

		String line;

		while ((line = br.readLine()) != null) {

			if (line.contains("id=\"Indoor\"")){
				break;
			}
			
			if (line.contains("id=\"Femmes\"") || line.contains("id=\"Women\"")) {
				sexe = SexeEnum.FEMININ;
				continue;
			}

			if (line.startsWith("<tr")) {
				lr = new LigneRecordWiki(idPays, sexe);
				continue;
			}

			if (line.contains("</tr>")) {

				if (lr == null || lr.getEpreuve() == null) {
					// System.err.println("=========" + lr);
					continue;
				}
				EpreuveEnum epreuveEnum = lr.getEpreuve();

				if (epreuveEnum != null) {
					
					if (lr.getPerf() == null){
						// System.err.println("-- " + lr.getEpreuve());
						continue;
					}
					
					lr.setPerf(cleanPerf(lr.getPerf(), lr.getEpreuve()));
					listeRecords.add(lr);
				}
				continue;
			}

			if (!line.startsWith("<td")) {
				continue;
			}

			// on traite les td : epreuve, perf, nom, date 
			// System.out.println(line);

			ParserRecordWiki.traiteLine(line, lr);

		}
		br.close();
		fr.close();
		return listeRecords;
	}
	
	private static String cleanPerf(String perf, EpreuveEnum epreuve) {

		String patternParenthes = "\\([^\\)]*\\)";

		perf = perf.replaceAll(patternParenthes, "");

		perf = perf.replace("&#160;", "").replace(" points", "").replace("points", "");
		perf = perf.replace(" min ", ".").replace(" s ", ".");

		// cas des chronos
		if (epreuve.name().startsWith("COURSE")){
			perf = perf.replace(" A", "");
		}
		
		
		return perf.trim();
	}

}

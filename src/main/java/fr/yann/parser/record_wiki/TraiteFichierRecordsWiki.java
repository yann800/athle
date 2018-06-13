package fr.yann.parser.record_wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.yann.model.enums.SexeEnum;
import fr.yann.model.record.LigneRecordWiki;

public class TraiteFichierRecordsWiki {

	public static void main(String[] args) throws Exception {

		Map<Integer, String> map = GetNomPaysWikiEn.getMap();

		for (Integer num : map.keySet()) {
			
			if (num != 69){
				continue;
			}
			
			System.out.println();
			// List<LigneRecordWiki> liste = traite("C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_wiki\\wiki\\pays(" + num + ")", num);
			List<LigneRecordWiki> liste = traite("/home/aek/yann/git/athle/record/pays" + num + ".html", num);

			// System.out.println("NOMBRE : " + liste.size() + "\n");
			for (LigneRecordWiki lr : liste) {
				if (lr.getPerf() == null) {
					continue;
				}

				System.out.println(lr.toStringSql());
			}
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

			if (line.startsWith("<tr>")) {
				lr = new LigneRecordWiki(idPays, sexe);
				continue;
			}

			if (line.startsWith("</tr>")) {

				if (lr != null && lr.getEpreuve() == null) {
					// System.err.println("=========" + lr);
					continue;
				}

				if (lr != null && !lr.getEpreuve().contains("arche") && !lr.getEpreuve().contains("walk") && !lr.getEpreuve().contains("erlin")) {

					// FIXME remetre
					if (lr.getEpreuve().contains("thlon")) {
						continue;
					}
					
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
	
	private static String cleanPerf(String perf, String epreuve) {

		String patternParenthes = "\\([^\\)]*\\)";

		perf = perf.replaceAll(patternParenthes, "");

		perf = perf.replace("&#160;", "").replace(" points", "").replace("points", "");
		perf = perf.replace(" min ", ".").replace(" s ", ".");

		// cas des chronos
		if (epreuve.contains("00")){
			perf = perf.replace(" A", "");
		}
		
		
		return perf.trim();
	}

}

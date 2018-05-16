package fr.yann.parser.record_wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.yann.model.enums.SexeEnum;
import fr.yann.model.record.LigneRecordWiki;

public class TraiteFichierRecordsWiki {

	public static void main(String[] args) throws Exception {

		Map<Integer, String> map = GetNomPays.getMap();


		for (Integer num : map.keySet()) {
			System.out.println(num + " " + map.get(num));
			List<LigneRecordWiki> liste = traite("C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_wiki\\wiki\\pays(" + num + ")", num);

			System.out.println("NOMBRE : " + liste.size() + "\n");
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

			if (line.contains("id=\"Femmes\"")) {
				sexe = SexeEnum.FEMININ;
				continue;
			}

			if (line.startsWith("<tr>")) {
				lr = new LigneRecordWiki(idPays, sexe);
				continue;
			}

			if (line.startsWith("</tr>")) {
				if (lr != null) {
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
}

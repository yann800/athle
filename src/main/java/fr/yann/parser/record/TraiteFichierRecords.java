package fr.yann.parser.record;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TraiteFichierRecords {

	public static void main(String[] args) throws Exception {

		traite("c:\\temp\\recordsEurope.html");

	}

	private static void traite(final String pathFile) throws Exception {

		File f = new File(pathFile);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while (line != null) {
			line = br.readLine();
			LigneRecord ligneRecord = ParserRecord.traiteLine(line);
			if (ligneRecord != null) {
				System.out.println(ligneRecord.toStringJson());
			}
		}



		br.close();
		fr.close();

		
	}

}

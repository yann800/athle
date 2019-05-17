package fr.yann.parser.interclub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import fr.yann.parser.interclub.csv.IC;

public class InterclubCsvToSql {


	public static void main(String[] args) {

		ArrayList<IC> perfs = new ArrayList<>();

		String pathFolder = "D:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\interclub\\csv\\";

		int annee = 2009;
		int tour = 1;

		File f = new File(pathFolder + annee + "_" + tour + ".txt");


		String line;

		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {

			while ((line = br.readLine()) != null) {

				if (!line.contains(";")) {
					continue;
				}

				IC ic = TraiteCsvInterclub.getIC(line, annee);

				if (ic != null) {
					perfs.add(ic);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (IC l : perfs) {
			System.out.println(l.toString());
		}
		System.out.println("*** END ***");
	}



	private static void save(String url, String nomFichierToSave) {
		try {
			System.out.println(nomFichierToSave + "\t" + url);
			final File file = new File("c:\\temp\\" + nomFichierToSave);
			FileUtils.writeStringToFile(file, "SELECT *****", "UTF-8");
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

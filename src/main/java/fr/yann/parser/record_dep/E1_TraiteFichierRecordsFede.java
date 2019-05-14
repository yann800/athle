package fr.yann.parser.record_dep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import fr.yann.model.enums.CategorieEnum;
import fr.yann.model.enums.SexeEnum;
import fr.yann.model.record.LigneRecordDep;

/**
 * 
 * HTML -> SQL
 *
 */
public class E1_TraiteFichierRecordsFede {

	public static final String PATH_PAGES_DEP = "D:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_dep\\pages";

	// Insert.main(path + ".sql");
	public static void main(String[] args) throws Exception {

		for (int num = 1; num < 96; num++) {
			
			if (num != 1) {
				continue;
			}
			
			StringBuffer sb = new StringBuffer();

			String str_num = num + "";
			if (num < 10) {
				str_num = "0" + num;
			}
			String path = PATH_PAGES_DEP + "\\F_ES_" + str_num + ".html";

			List<LigneRecordDep> liste = traite(path, num, SexeEnum.FEMININ, CategorieEnum.ES);

			// System.out.println("NOMBRE : " + liste.size() + "\n");
			for (LigneRecordDep lr : liste) {

				System.out.println(lr.toStringSql());
				sb.append(lr.toStringSql() + "\n");
			}
			// YANN SqlService.writeFile(path, sb.toString());
		}


	}

	private static List<LigneRecordDep> traite(String pathFile, int dep, SexeEnum sexeEnum, CategorieEnum catEnum) throws Exception {

		File f = new File(pathFile);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		// boolean bTrEncours = false;

		LigneRecordDep lr = null;
		List<LigneRecordDep> listeRecords = new ArrayList<>();

		String line;

		while ((line = br.readLine()) != null) {

			if (line.startsWith("<tr")) {
				lr = new LigneRecordDep(dep, sexeEnum, catEnum);
				ParserRecordDep.traiteLine(line, lr);
				listeRecords.add(lr);
			}
		}
		br.close();
		fr.close();
		return listeRecords;
	}
}

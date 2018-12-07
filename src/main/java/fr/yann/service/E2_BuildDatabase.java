package fr.yann.service;

import fr.yann.model.bilan.LastRang;
import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;
import fr.yann.parser.ParserHtml;

/**
 * CHECK DOUBLONS 
 * 
 * SELECT * FROM ligne l1, ligne l2 WHERE l1.id != l2.id AND l1.perf = l2.perf AND l1.nom = l2.nom AND l1.datePerf = l2.datePerf;
 * 
 * 1. on save les HTML
 * 2. on génère les SQL
 * 3. on exécute les SQL générés
 *
 */
public class E2_BuildDatabase {

	private static final int NOMBRE_PAGE_5 = 1; // pages 0 - 5

	public static void main(String[] args) {

		// importEpreuve(EpreuveEnum.COURSE_400, SexeEnum.FEMININ, NOMBRE_PAGE_5);
		importEpreuve(EpreuveEnum.COURSE_800, SexeEnum.MASCULIN, NOMBRE_PAGE_5);
		// trucBug();
	}

	//	private static void trucBug() {
	//		EpreuveEnum epreuveEnum = EpreuveEnum.COURSE_400;
	//		SexeEnum sexeEnum = SexeEnum.MASCULIN;
	//		String fileName = epreuveEnum.getCode() + "_"+ sexeEnum.getCodeStr() + "_2004_" + 5;
	//		String path = "C:\\Yann\\workspace_athle\\parser\\bilan\\" + epreuveEnum.getCode() + "\\" + fileName;
	//
	//		System.out.println(path);
	//
	//		try {
	//			LastRang lastRang = new LastRang();
	//			lastRang.setRang(1);
	//
	//			ParserHtml.ecrireSqlInsertValues(path + ".html", epreuveEnum, sexeEnum, lastRang);
	//			// Insert.main(path + ".sql");
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}		
	//	}

	private static void importEpreuve(final EpreuveEnum epreuveEnum, final SexeEnum sexeEnum, final int nbPages) {

		for (int annee = 2003; annee < 2004; annee++) {

			LastRang lastRang = new LastRang();
			lastRang.setRang(1);


			for (int i = 0; i < nbPages; i++) {

				// 400_M_2004_0.html
				String fileName = epreuveEnum.getCode() + "_"+ sexeEnum.getCodeStr() + "_" + annee + "_" + i;
				String path = "D:\\workspace_athle\\parser\\bilan\\" + epreuveEnum.getCode() + "\\" + fileName;
	
				System.out.println(path);
	
				try {
					ParserHtml.ecrireSqlInsertValues(path + ".html", epreuveEnum, sexeEnum, lastRang, annee);
					// Insert.main(path + ".sql");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

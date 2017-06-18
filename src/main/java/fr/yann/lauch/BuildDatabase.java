package fr.yann.lauch;

import fr.yann.dao.Insert;
import fr.yann.model.EpreuveEnum;
import fr.yann.model.SexeEnum;
import fr.yann.parser.LastRang;
import fr.yann.parser.Parser;

/**
 * CHECK DOUBLONS 
 * 
 * SELECT * FROM ligne l1, ligne l2 WHERE l1.id != l2.id AND l1.perf = l2.perf AND l1.nom = l2.nom AND l1.datePerf = l2.datePerf;
 * 
 * 
 *
 */
public class BuildDatabase {

	private static final int	NOMBRE_PAGE_5	= 6; // pages 0 - 5
	// private static final int	NOMBRE_PAGE_55	= 55;
	// private static final int	NOMBRE_PAGE_85	= 85;

	public static void main(String[] args) {

		// importEpreuve(EpreuveEnum.COURSE_400, SexeEnum.FEMININ, NOMBRE_PAGE_5);
		importEpreuve(EpreuveEnum.COURSE_400, SexeEnum.MASCULIN, NOMBRE_PAGE_5);
		// trucBug();
		
		// importEpreuve(EpreuveEnum.COURSE_800, SexeEnum.FEMININ, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_800, SexeEnum.MASCULIN, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_1500, SexeEnum.FEMININ, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_1500, SexeEnum.MASCULIN, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_3000, SexeEnum.FEMININ, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_3000, SexeEnum.MASCULIN, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_10_KM, SexeEnum.FEMININ, NOMBRE_PAGE_85);
		// importEpreuve(EpreuveEnum.COURSE_10_KM, SexeEnum.MASCULIN, NOMBRE_PAGE_85);

	}

	private static void trucBug() {
		EpreuveEnum epreuveEnum = EpreuveEnum.COURSE_400;
		SexeEnum sexeEnum = SexeEnum.MASCULIN;
		String fileName = epreuveEnum.getCode() + "_"+ sexeEnum.getCodeStr() + "_2004_" + 5;
		String path = "C:\\Yann\\workspace_athle\\parser\\bilan\\" + epreuveEnum.getCode() + "\\" + fileName;

		System.out.println(path);

		try {
			LastRang lastRang = new LastRang();
			lastRang.setRang(1);

			Parser.ecrireSqlInsertValues(path + ".html", epreuveEnum, sexeEnum, lastRang);
			// Insert.main(path + ".sql");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private static void importEpreuve(final EpreuveEnum epreuveEnum, final SexeEnum sexeEnum, final int nbPages) {

		for (int annee = 2006; annee < 2016; annee++) {

			LastRang lastRang = new LastRang();
			lastRang.setRang(1);


			for (int i = 0; i < nbPages; i++) {

				// 400_M_2004_0.html
				String fileName = epreuveEnum.getCode() + "_"+ sexeEnum.getCodeStr() + "_" + annee + "_" + i;
				String path = "C:\\Yann\\workspace_athle\\parser\\bilan\\" + epreuveEnum.getCode() + "\\" + fileName;
	
				System.out.println(path);
	
				try {
					Parser.ecrireSqlInsertValues(path + ".html", epreuveEnum, sexeEnum, lastRang);
					Insert.main(path + ".sql");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

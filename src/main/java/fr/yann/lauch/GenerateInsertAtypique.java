package fr.yann.lauch;

import java.io.IOException;

import fr.yann.model.EpreuveEnum;
import fr.yann.model.SexeEnum;
import fr.yann.parser.LastRang;
import fr.yann.parser.ParserHtml;

public class GenerateInsertAtypique {

	public static void main(String[] args) {

		// importEpreuve(EpreuveEnum.COURSE_800, SexeEnum.FEMININ, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_800, SexeEnum.MASCULIN, NOMBRE_PAGE_55);

		// importEpreuve(EpreuveEnum.COURSE_1500, SexeEnum.FEMININ, NOMBRE_PAGE_55);
		// importEpreuve(EpreuveEnum.COURSE_1500, SexeEnum.MASCULIN, NOMBRE_PAGE_55);

		// importEpreuve(EpreuveEnum.COURSE_10_KM, SexeEnum.FEMININ, NOMBRE_PAGE_85);
		// importEpreuve(EpreuveEnum.COURSE_10_KM, SexeEnum.MASCULIN, NOMBRE_PAGE_85);

	}

	private static void importEpreuve(final EpreuveEnum epreuveEnum, final SexeEnum sexeEnum, final int nbPages) {

		String idEpreuve = epreuveEnum.getCode();
		String strSexe = sexeEnum.getCodeStr();

		LastRang lastRang = new LastRang();
		lastRang.setRang(1);

		
		for (int i = 1; i < nbPages; i++) {

			String path = "C:\\workspace_athle\\parser\\bilan\\" + idEpreuve + "\\" + strSexe + "\\liste_" + String.format("%03d", i);

			// System.out.println(path);

			try {
				ParserHtml.ecrireSqlInsertValues(path + ".html", epreuveEnum, sexeEnum, lastRang);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}

}

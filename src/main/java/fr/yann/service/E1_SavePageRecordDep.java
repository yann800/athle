package fr.yann.service;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import fr.yann.model.enums.CategorieEnum;
import fr.yann.model.enums.SexeEnum;

public class E1_SavePageRecordDep {

	private static final String	URL_BASE_COM	= "https://bases.athle.com/asp.net/liste.aspx";
	private static final String	PARAM_DEFAULT	= "?frmpostback=true&frmbase=records&frmmode=1&frmespace=0&frmexploitation=actuel&frmtype=RD";

	public static void main(String[] args) {

		ArrayList<Record> records = new ArrayList<>();

		// #####################################
		SexeEnum sexeEnum = SexeEnum.MASCULIN;
		CategorieEnum categorieEnum = CategorieEnum.VE;
		// #####################################

		for (int dep = 1; dep < 96; dep++) {

			if (dep == 94) {
				continue;
			}


			Record b = new Record();
			b.categorie = categorieEnum;
			b.sexe = sexeEnum;
			b.departement = dep;

			// b.url = getUrlBilanAvant2004(ID_FRM_EPREUVE_400_AVANT_2004, annee, sexeEnum.getCodeStr(), numPage);
			b.url = getUrlRecord(sexeEnum, categorieEnum, getString(dep));
			records.add(b);

		}

		for (Record r : records) {
			// save(r.url, getFileName(r));
			System.out.println(getUrlRecord(r.sexe, r.categorie, getString3Car(r.departement)));
		}
	}

	private static String getString3Car(int departement) {
		if (departement < 10) {
			return "00" + departement;
		}
		return "0" + departement;
	}

	// sexe categorie_departement
	// M_VE_94.html
	private static String getFileName(Record b) {
		return b.sexe.getCodeStr() + "_" + b.categorie + "_" + getString(b.departement) + ".html";
	}

	private static String getString(int departement) {
		if (departement < 10) {
			return "0" + departement;
		}
		return departement + "";
	}

	private static String getUrlRecord(SexeEnum sexe, CategorieEnum cat, String departement) {
		return URL_BASE_COM + PARAM_DEFAULT +
				"&frmfamille=PI&frmcategorie=" + cat + "&frmsexe=" + sexe.getCodeStr() + "&frmniveau=DP&frmdepartement=" + departement;
	}

	private static void save(String url, String nomFichierToSave) {
		try {
			System.out.println(nomFichierToSave + "\t" + url);
			final File file = new File("c:\\temp\\" + nomFichierToSave);
			FileUtils.writeStringToFile(file, HttpURLConnectionAthle.getPage(url), "UTF-8");
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Record {

		SexeEnum		sexe;
		CategorieEnum	categorie;
		int				departement;
		int				frmPosition;
		String			url;

	}
}

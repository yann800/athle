package fr.yann.service;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class E1_SavePage {
	
	private static final String	URL_BASE_COM				= "http://bases.athle.com/asp.net/liste.aspx";
	private static final String	PARAM_DEFAULT				= "?frmpostback=true&frmbase=bilans&frmmode=1&frmespace=0";
	private static final String	PARAM_DEFAULT_AVANT_2004		= "?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0";

	private static final String	ID_FRM_EPREUVE_400_AVANT_2004	= "140400m";

	// private static final int	ID_FRM_EPREUVE_400			= 140;
	private static final int	ID_FRM_EPREUVE_800_INDOOR	= 209;
	private static final int	ID_FRM_EPREUVE_1000			= 210;
	private static final int	ID_FRM_EPREUVE_1500			= 215;

	// ======= AVANT 2004 =======			
	// frmsaison indique l'annee. 5 : 2002, 8 : 2003. Attention cela remplace frmannee
	// 
	// page 1 : [&frmposition=] page 2 : &frmposition=1
	// 
	// http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=5&frmtype=Plein+air&frmepreuve=140400m&frmsexe=M	
	// http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=8&frmtype=Plein+air&frmepreuve=140400m&frmsexe=M
	// http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=8&frmtype=Plein+air&frmepreuve=140400m&frmsexe=M&frmposition=1

	// ---------------------------
	// la vraie (page 2 400 2002):
	// http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=8&frmtype=Plein+air&frmepreuve=140400m&frmplaces=&frmnaissance=&frmvent=&frmcategorie=&frmsexe=M&frmligue=&frmnom=&frmlicence=&frmclub=&frmposition=1
	// généréé
	// http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=5&frmtype=Plein+air&frmepreuve=140400m&frmsexe=M&frmposition=1
	// ---------------------------

	//			=== 800
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2012&&frmepreuve=208&frmsexe=M&frmposition=2
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2013&frmsexe=M&frmepreuve=208
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2015&frmsexe=M&frmepreuve=208
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2015&&frmepreuve=208&frmsexe=M&frmposition=2
	//			
	//			=== 1500 2017
	//			
	//			===
	//			NE AVANT...
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2015&frmepreuve=208&frmcategorie=&frmsexe=M&frmnationalite=&frmamaxi=1980
	//			
	//			=== Longueur femme
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2012&frmepreuve=503&frmsexe=F&frmcategorie=ES
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2011&frmepreuve=208&frmsexe=M&frmcategorie=VE
	//			=== 10 km
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2014&frmepreuve=261&frmsexe=M&frmvent=VR
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2014&frmepreuve=261&frmsexe=M&frmvent=VR&frmposition=2

	/**
	
	 * @param args 
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		
		ArrayList<Bilan> bilans = new ArrayList<>();

		// sexe annee epreuve page
		// M_2006_800_1.html
		
		SexeEnum sexeEnum = SexeEnum.MASCULIN;

		for (int annee = 2002; annee < 2004; annee++) {

			for (int numPage = 0; numPage < 6; numPage++) {
				Bilan b = new Bilan();
				b.epreuve = EpreuveEnum.COURSE_400;
				b.sexe = sexeEnum;
				b.annee = annee;
				b.frmPosition = numPage;

				b.url = getUrlBilanAvant2004(ID_FRM_EPREUVE_400_AVANT_2004, annee, sexeEnum.getCodeStr(), numPage);
				bilans.add(b);

			}
		}

		for (Bilan b : bilans) {
			save(b.url, getFileName(b));
		}
	}

	private static String getFileName(Bilan b) {
		return b.epreuve.getCode() + "_" + b.sexe.getCodeStr() + "_" + b.annee + "_" + b.frmPosition + ".html";
	}

	private static String getUrlBilan(int idFrmEpreuve, int annee, String sexe, int numPage) {
		return URL_BASE_COM + PARAM_DEFAULT + "&frmannee=" + annee + "&frmepreuve=" + idFrmEpreuve + "&frmsexe=" + sexe + "&frmposition=" + numPage;
	}

	private static String getUrlBilanAvant2004(String idFrmEpreuve, int annee, String sexe, int numPage) throws Exception {

		// frmsaison indique l'annee. 5 : 2002, 8 : 2003
		int saison = 0;
		if (annee == 2002) {
			saison = 5;
		} else if (annee == 2003) {
			saison = 8;
		} else {
			throw new Exception("annee [" + annee + "]  non prévue");
		}

		String frmPosition = "";
		if (numPage > 1) {
			frmPosition = "&frmposition=" + (numPage - 1);
		}
		return URL_BASE_COM + PARAM_DEFAULT_AVANT_2004 + "&frmsaison=" + saison + "&frmtype=Plein+air" + "&frmepreuve=" + idFrmEpreuve + "&frmsexe=" + sexe + frmPosition;
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

	static class Bilan {

		SexeEnum	sexe;
		EpreuveEnum	epreuve;
		int			annee;
		int			frmPosition;
		String		url;

	}
}

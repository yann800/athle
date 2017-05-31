package fr.yann.fiche;

import java.util.ArrayList;

import fr.yann.model.EpreuveEnum;
import fr.yann.model.SexeEnum;

public class SavePage {
	
	private static final String	URL_BASE_COM				= "http://bases.athle.com/asp.net/liste.aspx";
	private static final String	PARAM_DEFAULT				= "?frmpostback=true&frmbase=bilans&frmmode=1&frmespace=0";

	private static final int	ID_FRM_EPREUVE_400			= 140;
	private static final int	ID_FRM_EPREUVE_800_INDOOR	= 209;
	private static final int	ID_FRM_EPREUVE_1000			= 210;
	private static final int	ID_FRM_EPREUVE_1500			= 215;
	//			URL_BASE_COM PARAM_DEFAULT &frmannee=2012&&frmepreuve=140&frmsexe=M&frmposition=2
	//			
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
		
		for (int annee = 2006; annee < 2017; annee++) {

			Bilan b = new Bilan();
			b.url = getUrlBilan(ID_FRM_EPREUVE_400, annee, SexeEnum.MASCULIN.getCodeStr());
			bilans.add(b);
		}

		// save(getUrlRecords("EU", SexeEnum.MASCULIN.getCodeStr()), "recordsEurope.html");

		for (Bilan b : bilans) {
			save(b.url, getFileName(b));
		}
	}

	private static String getFileName(Bilan b) {
		return b.sexe.getCodeStr() + "_" + b.annee + "_" + b.frmPosition + ".html";
	}

	private static String getUrlRecords(String niveau, String sexe) {
		return URL_BASE_COM + "?frmpostback=true&frmbase=records&frmmode=1&frmespace=0&frmexploitation=actuel&frmtype=RD&frmcategorie=&frmsexe=&frmniveau=" + niveau;
	}

	private static String getUrlBilan(int idFrmEpreuve, int annee, String sexe) {
		return URL_BASE_COM + PARAM_DEFAULT + "&frmannee=" + annee + "&frmepreuve=" + idFrmEpreuve + "&frmsexe=" + sexe;
	}

	private static void save(String url, String nomFichierToSave) {
		try {
			//			final File file = new File("c:\\temp\\" + nomFichierToSave);
			//			FileUtils.writeStringToFile(file, HttpURLConnectionAthle.getPage(url), "UTF-8");

			System.out.println(nomFichierToSave);
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

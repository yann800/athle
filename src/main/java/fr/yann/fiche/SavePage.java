package fr.yann.fiche;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import fr.yann.model.SexeEnum;

public class SavePage {
	
	private static final String	URL_BASE_COM				= "http://bases.athle.com/asp.net/liste.aspx";
	private static final String	PARAM_DEFAULT				= "?frmpostback=true&frmbase=bilans&frmmode=1&frmespace=0";

	private static final int	ID_FRM_EPREUVE_400			= 140;
	private static final int	ID_FRM_EPREUVE_800_INDOOR	= 209;

	/**
	
	=== 400
	URL_BASE_COM PARAM_DEFAULT &frmannee=2012&&frmepreuve=140&frmsexe=M&frmposition=2
	
	=== 800
	URL_BASE_COM PARAM_DEFAULT &frmannee=2012&&frmepreuve=208&frmsexe=M&frmposition=2
	URL_BASE_COM PARAM_DEFAULT &frmannee=2013&frmsexe=M&frmepreuve=208
	URL_BASE_COM PARAM_DEFAULT &frmannee=2015&frmsexe=M&frmepreuve=208
	URL_BASE_COM PARAM_DEFAULT &frmannee=2015&&frmepreuve=208&frmsexe=M&frmposition=2
	
	=== 1500 2017
	URL_BASE_COM PARAM_DEFAULT &frmannee=2017&frmepreuve=215&frmsexe=M
	
	===
	NE AVANT...
	URL_BASE_COM PARAM_DEFAULT &frmannee=2015&frmepreuve=208&frmcategorie=&frmsexe=M&frmnationalite=&frmamaxi=1980
	
	=== 1000
	URL_BASE_COM PARAM_DEFAULT &frmannee=2013&frmsexe=M&frmepreuve=210
	URL_BASE_COM PARAM_DEFAULT &frmannee=2014&frmsexe=M&frmepreuve=210
	
	=== 1500
	URL_BASE_COM PARAM_DEFAULT &frmannee=2013&frmsexe=M&frmepreuve=215
	
	=== 800 indoor
	URL_BASE_COM PARAM_DEFAULT &frmannee=2012&frmepreuve=209&frmsexe=M
	URL_BASE_COM PARAM_DEFAULT &frmannee=2013&frmepreuve=209&frmsexe=M
	URL_BASE_COM PARAM_DEFAULT &frmannee=2014&frmepreuve=209&frmsexe=M
	
	=== Longueur femme
	URL_BASE_COM PARAM_DEFAULT &frmannee=2012&frmepreuve=503&frmsexe=F&frmcategorie=ES
	URL_BASE_COM PARAM_DEFAULT &frmannee=2011&frmepreuve=208&frmsexe=M&frmcategorie=VE
	
	=== 10 km
	URL_BASE_COM PARAM_DEFAULT &frmannee=2014&frmepreuve=261&frmsexe=M&frmvent=VR
	URL_BASE_COM PARAM_DEFAULT &frmannee=2014&frmepreuve=261&frmsexe=M&frmvent=VR&frmposition=2
	 * @param args 
	 * @throws Exception 
	
	 */

	public static void main(String[] args) throws Exception {

		
		ArrayList<String> urls = new ArrayList<>();
		
		for (int annee = 2006; annee < 2017; annee++) {

			String url = getUrlBilan(ID_FRM_EPREUVE_400, annee, SexeEnum.MASCULIN.getCodeStr());
			urls.add(url);
		}

		save(getUrlRecords("EU", SexeEnum.MASCULIN.getCodeStr()), "recordsEurope.html");

		for (String page : urls) {
			// save(page, "bilan_800_M.html");
			System.out.println(page);
		}
	}

	private static String getUrlRecords(String niveau, String sexe) {
		return URL_BASE_COM + "?frmpostback=true&frmbase=records&frmmode=1&frmespace=0&frmexploitation=actuel&frmtype=RD&frmcategorie=&frmsexe=&frmniveau=" + niveau;
	}
	private static String getUrlBilan(int idFrmEpreuve, int annee, String sexe) {
		return URL_BASE_COM + PARAM_DEFAULT + "&frmannee=" + annee + "&frmepreuve=" + idFrmEpreuve + "&frmsexe=" + sexe;
	}

	private static void save(String url, String nomFichierToSave) {
		try {
			final File file = new File("c:\\temp\\" + nomFichierToSave);
			FileUtils.writeStringToFile(file, HttpURLConnectionAthle.getPage(url), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

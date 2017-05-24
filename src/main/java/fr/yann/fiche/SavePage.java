package fr.yann.fiche;

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
	
	 */

	public static void main(String[] args) {

		HttpURLConnectionAthle http = new HttpURLConnectionAthle();
		
		String page = URL_BASE_COM + PARAM_DEFAULT + "&frmannee=2012&frmepreuve=" + ID_FRM_EPREUVE_800_INDOOR + "&frmsexe=M";

		
//		try {
//			http.sendGet(urlFiche);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}

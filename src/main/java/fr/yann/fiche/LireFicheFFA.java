package fr.yann.fiche;

public class LireFicheFFA {

	

	public static void main(String[] args) {

		
		
		String nom = "ABDELKADER";
		String prenom = "Yann";
		
		HttpURLConnectionAthle http = new HttpURLConnectionAthle();
		

		String seq = "1226306";
		
		String urlFiche = "http://bases.athle.com/asp.net/athletes.aspx?base=bilans&seq=" + strToHex(seq);
		
		String realUrlFiche = "http://bases.athle.com/asp.net/athletes.aspx?base=bilans&seq=5049495049504554485151484554";
		
		// String searchByNom_2003 = "http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=8&frmtype=Plein+air&frmsexe=M&frmnom=ABDELKADER";
		// String url_800_2003 = "http://bases.athle.com/asp.net/liste.aspx?frmpostback=true&frmbase=bilansa&frmmode=1&frmespace=0&frmsaison=8&frmtype=Plein+air&frmepreuve=208800m&frmsexe=M";


		System.out.println(urlFiche);
		System.out.println(realUrlFiche);
		
//		try {
//			http.sendGet(urlFiche);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	
	public static String strToHex(String strId) {
	    
		String hexreturn = "";
	    
		
	    for (int i = 1; i <= strId.length(); i++) {
	        hexreturn = hexreturn + (99 - strId.charAt(i - 1));
	        hexreturn = hexreturn + (strId.charAt(i - 1));
	    }
	    return hexreturn;
	}

}

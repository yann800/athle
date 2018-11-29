package fr.yann.service;

import java.util.Arrays;
import java.util.List;

public class Comparaison {

	static String[]	tab1	= new String[] {
			"JEMMAL Abdelghani",
			"FAVIER Francois-rene",
			"KHEZZANE Noreddine",
			"CARANTON Laurent",
			"ATMANI Khalid",
			"VERDOL Widdy",
			"MOUQUET Sylvain",
			"ROUYER Thomas",
			"BEN-HOUDJA Mohammed",
			"EL YAMANI Mohammed",
			"MERROUNE Mohamed",
			"RUIZ Pascal",
			"GUIVARCH Pierre-yves",
			"JARRY Alain",
			"PERCEVAULT Philippe",
			"CHOUKRI Mohamed",
			"HARDY David",
			"ABBES Mohamed",
			"ALLONGUE Antoine",
			"ABACHAD Adil",
			"FARIA Rolando",
			"SAIM Ahmed (Alg)",
			"CROIZIER Lilian",
			"VILFEU Ludovic",
			"ROBILLARD Damien",
			"HERCOUET Didier",
			"GRAS Herve",
			"ROSSE Christophe",
			"PHILIPONA Bruno",
			"VIOLARD Frederic",
			"ARRAS Mohamed",
			"BENHAMOU Michael",
			"MAO Stephane",
			"TASSIN Jerome",
			"DESFOUX Yoann",
			"URRUTIA Laurent",
			"GHIANI Marc",
			"SAILLY Patrice",
			"HUE Sylvain",
			"ABDELKADER Yann",
			"GUEGANO Raphael",
			"LAMRABAT Zakarya",
			"DIAZ MARTINEZ Pablo (Esp)",
			"FISCHER Marc",
			"COACOLO Didier",
			"BEAUFORT Sebastien",
			"MAROLLES Frederic",
			"MEGHAZI Mhamed",
			"DELEPINE Sylvain",
			"PELLETIER Gilles",
			"BARBETTE Frederic",
			"RODRIGUES DOS SANTOS Joao",
			"MASTOURI Djamel",
			"NOIRY Julien",
			"BERTRAND Nicolas",
			"MARTIN Stephane",
			"GRONDIN Alain",
			"BETTAHAR Fodil (Alg)",
			"TAJIRI Franck",
	};

	static String[]	tab2	= new String[] {
			"GRUNDTNER Willy",
			"FAVIER Francois-rene",
			"EL HOUSNI Mounir",
			"LECIEUX Thomas",
			"CARANTON Laurent",
			"BENHAMOU Michael",
			"BOUBEKEUR Achour",
			"GANDELOT Benoit",
			"MARTINHO Ezequiel",
			"ABACHAD Adil",
			"ARRAS Mohamed",
			"DIANI Ilyas",
			"BEN-HOUDJA Mohammed",
			"DESTREMON Manuel",
			"DUHALDE Miguel",
			"FARIA Rolando",
			"TOURNIER Martial",
			"MOUILLERON Jean-francois",
			"BEAU Guillaume",
			"LACHEREST Sebastien",
			"ALZOULEH Kamel (Alg)",
			"PERTHUIS Nicolas",
			"ABDELKADER Yann",
			"CHOUKRI Mohamed",
			"BLANCHARD Nicolas",
			"GHIANI Marc",
			"BERTHO-LAUNAY Cyrille",
			"FAUCHEUX Cerile",
			"BARAONA Christophe",
			"HANNOUDI Mounir",
			"CURADO Pedro (Por)",
			"HAKMI Abdelaziz",
			"GRANGER David",
			"BROY Frederic",
			"MARCOVICI Jean-baptiste",
			"JACQUIN Philippe",
			"DUPONT Marc",
			"LAFARGUE Victorien",
			"LE MOING Joel",
			"SMITH Julien",
			"CHEBLAL Hassan",
			"TONI Stephane",
			"IKHADDALENE Mouloud",
			"LINTIGNAT Boris",
			"GILLES Fabrice",
			"JOUGLAS Franck",
			"AUGIER Jacky",
			"SACHIER Laurent",
			"DOSPAZOS Jose",
			"LIBERMAN Bernard",
	};

	public static void main(String[] args) {

		List<String> list1 = Arrays.asList(tab1);
		List<String> list2 = Arrays.asList(tab2);

		for (String a : list1) {
			if (list2.contains(a)) {
				System.out.println("{ from: \"Inter2017\", fromPort: \"" + a + "\", to: \"Creteil2018\", toPort: \"" + a + "\" },");
			}
		}

	}

}

package fr.yann.parser.record;

import fr.yann.model.SexeEnum;

public class LigneRecord {

	private String	epreuve;
	private String	perf;
	private String	nom;
	private int			annee;
	private SexeEnum	sexe;

	public LigneRecord(String epreuve, String perf, String nom, int annee, SexeEnum sexe) {
		super();
		this.epreuve = epreuve;
		this.perf = perf;
		this.nom = nom;
		this.annee = annee;
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "LigneRecord [epreuve:" + epreuve + ", perf:" + perf + ", nom:" + nom + ", annee:" + annee + ", sexe:" + sexe + "]";
	}


	public String toStringJson() {
		String str = "{epreuve:'" + getEpreuve() + "', nom:'" + nom + "', perf:'" + getPerf(perf) + "', annee:'" + annee + "'},";
		return str;
	}

	private String getEpreuve() {
		return epreuve.replace("m", "").replace(" ", "");
	}
	private String getPerf(String str) {

		String result = str.replace("''", ".").replace("'", ".");

		int indexParenthese = str.indexOf("(");

		if (indexParenthese > 0) {
			result = str.substring(0, indexParenthese - 1);
		}
		return result;
	}


}

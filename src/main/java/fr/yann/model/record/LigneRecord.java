package fr.yann.model.record;

import fr.yann.model.enums.SexeEnum;

public class LigneRecord {

	private String	categorie;
	private String	epreuve;
	private String	perf;
	private String	nom;
	private int			age;
	private int	annee;
	private SexeEnum	sexe;

	public LigneRecord(String categorie, String epreuve, String perf, String nom, int age, int annee, SexeEnum sexe) {
		super();
		this.categorie = categorie;
		this.epreuve = epreuve;
		this.perf = perf;
		this.nom = nom;
		this.age = age;
		this.annee = annee;
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "LigneRecord [categorie: " + categorie + ", epreuve:" + epreuve + ", perf:" + perf + ", nom:" + nom + ", annee:" + annee + ", sexe:" + sexe + "]";
	}


	public String toStringJson() {
		String str = "{e:'" + getEpreuve() + "', nom:'" + nom + "', age:'" + age + "', perf:'" + getPerf(perf) + "', annee:'" + annee + "'},";
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
	public String getCategorie() {
		return categorie;
	}


}

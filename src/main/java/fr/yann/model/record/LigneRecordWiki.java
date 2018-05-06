package fr.yann.model.record;

import fr.yann.model.enums.SexeEnum;

public class LigneRecordWiki {

	private String		epreuve;
	private String		perf;
	private String		nom;
	private int			age;
	private int			annee;
	private SexeEnum	sexe;

	public LigneRecordWiki(SexeEnum sexe) {
		super();
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "LigneRecord [epreuve:" + epreuve + ", perf:" + perf + ", nom:" + nom + ", annee:" + annee + ", sexe:" + sexe + "]";
	}

	public String toStringJson() {
		String str = "{e:'" + getEpreuve() + "', nom:'" + nom + "', age:'" + age + "', perf:'" + getPerf(perf) + "', annee:'" + annee + "'},";
		return str;
	}

	public String getEpreuve() {
		return epreuve; // .replace("m", "").replace(" ", "");
	}

	private String getPerf(String str) {

		String result = str.replace("''", ".").replace("'", ".");

		int indexParenthese = str.indexOf("(");

		if (indexParenthese > 0) {
			result = str.substring(0, indexParenthese - 1);
		}
		return result;
	}

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public SexeEnum getSexe() {
		return sexe;
	}

	public void setSexe(SexeEnum sexe) {
		this.sexe = sexe;
	}

	public void setEpreuve(String epreuve) {
		this.epreuve = epreuve;
	}

}

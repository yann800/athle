package fr.yann.model.record;

import fr.yann.model.enums.SexeEnum;

public class LigneRecordWiki {

	private String		epreuve;
	private String		perf;
	private String		nom;
	private int			age;
	private int			annee;
	private SexeEnum	sexe;
	private int			idPays;

	public LigneRecordWiki(int idPays, SexeEnum sexe) {
		super();
		this.idPays = idPays;
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "LigneRecord [epreuve:" + getEpreuve() + ", perf:" + getPerf() + ", nom:" + getNom() + ", annee:" + getAnnee() + ", sexe:" + sexe + "]";
	}

	public String toStringJson() {
		String str = "{e:'" + getEpreuve() + "', nom:'" + nom + "', age:'" + age + "', perf:'" + getPerf(perf) + "', annee:'" + annee + "'},";
		return str;
	}

	public String toStringSql() {
		String str = "INSERT INTO record (idPays, sexe, epreuve, nom, perf, annee) VALUES ("
				+ idPays + ","
				+ sexe.getCodeInt() + ",'"
				+ getEpreuve() + "', '"
				+ nom + "','"
				+ getPerf(perf) + "',"
				+ annee + ");";
		return str;
	}
	public String getEpreuve() {
		if (epreuve == null) {
			return null;
		}
		return epreuve.replace(" m", ".").replace("&#160;", "").trim();
	}

	private String getPerf(String str) {

		String result = str.replace("''", ".")
				.replace("'", ".")
				.replace("&#160;", "")
				.replace(" s ", ".")
				.replace(" min ", ".")
				.trim();

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

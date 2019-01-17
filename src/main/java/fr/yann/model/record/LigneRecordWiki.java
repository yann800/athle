package fr.yann.model.record;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class LigneRecordWiki {

	private int			rang;
	private EpreuveEnum	epreuve;
	private String		perf;
	private String		nom;
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
		String str = "{e:'" + getEpreuve() + "', nom:'" + nom  + "', perf:'" + getPerf(perf) + "', annee:'" + annee + "'},";
		return str;
	}

	public String toStringSql() {
		String str = "INSERT INTO record (idPays, sexe, epreuve, nom, perf, annee) VALUES ("
				+ idPays + ","
				+ sexe.getCodeInt() + ",'"
				+ getEpreuve().getCode() + "', '"
				+ nom + "','"
				+ getPerf(perf) + "',"
				+ annee + ");";
		return str;
	}
	public EpreuveEnum getEpreuve() {
		return epreuve;
	}

	private String getPerf(String str) {

		String result = str.replace("''", ".")
				.replace("'", ".")
				.replace(",", ".")
				.replace("&#160;", "")
				.replace(" s ", ".")
				.replace("s", ".")
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

	public void setEpreuve(EpreuveEnum epreuve) {
		this.epreuve = epreuve;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public int getIdPays() {
		return idPays;
	}

	public void setIdPays(int idPays) {
		this.idPays = idPays;
	}
}

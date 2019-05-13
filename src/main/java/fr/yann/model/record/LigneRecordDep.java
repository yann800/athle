package fr.yann.model.record;

import fr.yann.model.enums.CategorieEnum;
import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class LigneRecordDep {

	private int				rang;
	private CategorieEnum	cat;
	private EpreuveEnum		epreuve;
	private String			perf;
	private String			nom;
	private int				annee;
	private SexeEnum		sexe;
	private int				idDep;

	public LigneRecordDep(int idDep, SexeEnum sexe, CategorieEnum cat) {
		super();
		this.idDep = idDep;
		this.sexe = sexe;
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "LigneRecordDep [rang=" + rang + ", epreuve=" + epreuve + ", perf=" + perf + ", nom=" + nom + ", annee=" + annee + ", sexe=" + sexe + ", idDep=" + idDep + "]";
	}

	public String toStringJson() {
		String str = "{e:'" + getEpreuve() + "', nom:'" + nom + "', perf:'" + getPerf(perf) + "', annee:'" + annee + "'},";
		return str;
	}

	public String toStringSql() {
		if (epreuve == null) {
			return "";
		}
		String str = "INSERT INTO record (idDep, sexe, cat, epreuve, nom, perf, annee) VALUES ("
				+ idDep + ","
				+ sexe.getCodeInt() + ",'"
				+ cat + "',"
				+ epreuve.getCode() + "', '"
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

	public int getIdDep() {
		return idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}
}

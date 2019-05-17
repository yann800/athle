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
	private String			naissance;
	private String			club;
	private String			lieu;
	private String			date;
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
		return "LigneRecordDep [rang=" + rang + ", epreuve=" + epreuve + ", perf=" + perf + ", nom=" + nom + ", annee=" + getAnnee() + ", sexe=" + sexe + ", idDep=" + idDep + "]";
	}

	public String toStringJson() {
		String str = "{e:'" + getEpreuve() + "', nom:'" + nom + "', perf:'" + getPerf(perf) + "', annee:'" + getAnnee() + "'},";
		return str;
	}

	public String toStringSql() {
		if (epreuve == null || perf == null) {
			return "";
		}
		String str = "INSERT INTO record_dep (idDep, sexe, categorie, epreuve, nom, perf, annee, lieu) VALUES ("
				+ idDep + ","
				+ sexe.getCodeInt() + ",'"
				+ cat + "','"
				+ epreuve.getCode() + "', '"
				+ nom + "','"
				+ getPerf(perf) + "',"
				+ getAnnee() + ", '"
				+ getLieu() + "');";
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
		try {
			String annee = getDate().substring(6, 8);
			return Integer.parseInt(annee);
		} catch (Exception e) {
			System.err.println(getDate() + " " + e.getMessage());
			return 0;
		}
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

	public String getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
}

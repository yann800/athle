package fr.yann.model.bilan;

public class LigneBilan {

	private static final String SEPARATOR_TD = "</td><td>";
	private int		rang;
	private String	perf;
	private String	nom;
	private String	prenom;
	private String	club;
	private String	ligue;
	private int		anneeNaissance;
	private String	datePerf;
	private String	ville;
	private String	idEpreuve;
	private int		sexe;
	private String				pays;
	private int					annee;

	public LigneBilan(int rang, String perf, String nom, String prenom, String club, String ligue, int anneeNaissance, String datePerf, String ville, String idEpreuve, int sexe,
			String pays, int annee) {
		super();
		this.setRang(rang);
		this.perf = perf;
		this.nom = nom;
		this.prenom = prenom;
		this.club = club;
		this.ligue = ligue;
		this.ville = ville;
		this.anneeNaissance = anneeNaissance;
		this.datePerf = datePerf;
		this.idEpreuve = idEpreuve;
		this.sexe = sexe;
		this.pays = pays;
		this.annee = annee;
	}

	@Override
	public String toString() {

		String str = "(" + rang + ",'"
				+ perf + "','"
				+ nom + "','" + prenom + "'," + sexe + ",'"
				// + pays + "','"
				+ club + "','" + ligue + "','" + datePerf
				+ "'," + anneeNaissance + ",'" + ville + "'," + idEpreuve + "," + annee;

		if ((rang % 250) == 0) {
			return str + ");";
		}
		return str + "),";

	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public String toStringInsert() {
		String str = "INSERT INTO `ligne` (`rang`, `perf`, `nom`, `prenom`, `sexe`, `idPays`, `club`, `ligue`, `datePerf`, `naissance`, `ville`, `idEpreuve`) VALUES(" + rang + ",'"
				+ perf + "','"
				+ nom + "','" + prenom + "'," + sexe + ",'"
				+ pays + "','" + club + "','" + ligue + "','" + datePerf
				+ "'," + anneeNaissance + ",'" + ville + "'," + idEpreuve + ");";

		return str;
	}
	
	public String toStringHtmlTrOfTable() {
		return "<tr><td>" + rang + SEPARATOR_TD + perf 
				+ SEPARATOR_TD + nom + SEPARATOR_TD + prenom + SEPARATOR_TD + pays
				+ SEPARATOR_TD + club + SEPARATOR_TD + anneeNaissance
				+ SEPARATOR_TD + datePerf + SEPARATOR_TD + ville
				+ "</td></tr>";
	}

}

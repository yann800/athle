package fr.yann.parser;

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
	private int		idEpreuve;
	private int		sexe;

	public LigneBilan(int rang, String perf, String nom, String prenom, String club, String ligue, int anneeNaissance, String datePerf, String ville, int idEpreuve, int sexe) {
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
	}

	@Override
	public String toString() {
		String str = "(" + rang + ",'"
				+ perf + "','"
				+ nom + "','" + prenom + "'," + sexe + ",'"
				+ club + "','" + ligue + "','" + datePerf
				+ "'," + anneeNaissance + ",'" + ville + "'," + idEpreuve;

		if ((rang % 200) == 0) {
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
		String str = "INSERT INTO `ligne` (`rang`, `perf`, `nom`, `prenom`, `sexe`, `club`, `ligue`, `datePerf`, `naissance`, `ville`, `idEpreuve`) VALUES(" + rang + ",'"
				+ perf + "','"
				+ nom + "','" + prenom + "'," + sexe + ",'"
				+ club + "','" + ligue + "','" + datePerf
				+ "'," + anneeNaissance + ",'" + ville + "'," + idEpreuve + ");";

		return str;
	}
	
	public String toStringHtmlTrOfTable() {
		return "<tr><td>" + rang + SEPARATOR_TD + perf 
				+ SEPARATOR_TD + nom + SEPARATOR_TD + prenom
				+ SEPARATOR_TD + club + SEPARATOR_TD + anneeNaissance
				+ SEPARATOR_TD + datePerf + SEPARATOR_TD + ville
				+ "</td></tr>";
	}

}

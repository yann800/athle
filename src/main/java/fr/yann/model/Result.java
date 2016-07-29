package fr.yann.model;

public class Result {

	String	nom;
	private int		annee;
	int		rang;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public Result(String nom, int annee, int rang) {
		this.nom = nom;
		this.annee = annee;
		this.rang = rang;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

}

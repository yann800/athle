package fr.yann.parser.interclub.csv;

import fr.yann.model.enums.CategorieEnum;
import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

/**
CREATE TABLE IF NOT EXISTS ic (
  id        int(11) NOT NULL AUTO_INCREMENT,
  nom       varchar(30) NOT NULL,
  categorie varchar(4)  DEFAULT NULL,
  sexe      char(1)     NOT NULL,
  epreuve   varchar(12) NOT NULL,
  perf      varchar(12) NOT NULL,
  points    int(4)      DEFAULT NULL,
  niveau    varchar(4)  NOT NULL,
  naissance int(11)     NOT NULL,
  annee     int(11)     DEFAULT NULL,
  datePerf  varchar(10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
*/
public class IC {

	public String			nom;
	public CategorieEnum	categorie;
	public SexeEnum			sexe;
	public EpreuveEnum		epreuve;
	public String			perf;
	public int				points;
	public String			niveau;
	public String			naissance;
	public int				annee;
	public String			date;


	public String toSql() {
		return "INSERT INTO IC (nom, categorie, sexe, epreuve, perf, points, niveau, naissance, annee, date)"
				+ nom + ", " + epreuve + ", " + perf + ", " + points + ", " + niveau + ", " + naissance + ", " + annee
				+ ", " + date + ");";
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCategorie(CategorieEnum categorie) {
		this.categorie = categorie;
	}

	public void setSexe(SexeEnum sexe) {
		this.sexe = sexe;
	}

	public void setEpreuve(EpreuveEnum epreuve) {
		this.epreuve = epreuve;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
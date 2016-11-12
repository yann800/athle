package fr.yann.csv;

import java.util.Set;
import java.util.TreeSet;

public class Person {

	String nom;
	String prenom;
	String club1;
	String club2;
	String club3;
	Set<String> clubs = new TreeSet<>();

	public Person(String nom, String prenom, String club1, String club2, String club3) {
		this.nom = nom.replace("\"", "");
		this.prenom = prenom.replace("\"", "");
		this.club1 = club1.replace("\"", "");
		this.club2 = club2.replace("\"", "");
		this.club3 = club3.replace("\"", "");
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getClub1() {
		return club1;
	}

	public String getClub2() {
		return club2;
	}

	public String getClub3() {
		return club3;
	}

	@Override
	public String toString() {
		return "<tr><td>" + clubs.size() + "</td><td>" + nom + "</td><td>" + prenom + "</td><td>" + clubs.toString() + "</td></tr>";
	}

	// si on ne définit pas hashCode le test d'égalité échoue (ça ne suffit pas le test sur les champ seulement)
	@Override
	public int hashCode() {
		  int hash = 1;
		  hash = hash * 31 + nom.hashCode();
		  hash = hash * 31 + prenom.hashCode();
		  return hash;
	}

	@Override
	public boolean equals(Object obj) {

		Person p = (Person) obj;
		if (!p.getNom().equals(getNom())){
			return false;
		}
		if (!p.getPrenom().equals(getPrenom())){
			return false;
		}
		return true;
	}

	public void addClubs(String c1, String c2, String c3) {
		clubs.add(c1.replace("\"", ""));
		clubs.add(c2);
		clubs.add(c3);
	}

	
	
}

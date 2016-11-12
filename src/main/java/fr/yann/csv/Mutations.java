package fr.yann.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mutations {

	public static void main(String[] args) {

		List<Person> listAllPersons = readCSV();

		List<Person> listPersons = cleanListe(listAllPersons);

		listPersons.stream().filter(p -> p.clubs.size() == 5).forEach(System.out::println);
		
		listPersons.stream().filter(p -> p.clubs.size() == 4).forEach(System.out::println);
		
		// listPersons.stream().filter(p -> p.clubs.size() == 3).forEach(System.out::println);		
	}

	// nom, prenom, club1, club2, club3
	static List<Person> readCSV() {

		try (BufferedReader br = new BufferedReader(new FileReader("/home/mak/Bureau/Yann/workspace/athle/src/main/java/fr/yann/csv/mutations.csv"))) {

			List<Person> persons = br.lines().map(mapToPerson)
					// .limit(100)
					.collect(Collectors.toList());
			return persons;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	static List<Person> cleanListe(List<Person> listAllPersons) {

		List<Person> cleanPersons = new ArrayList<>();

		// recherche des personnes qui figurent plus de 3 fois
		for (Person p : listAllPersons) {

			Person personnePresente = getFromListe(p.getNom(), p.getPrenom(), cleanPersons);

			if (personnePresente == null) {
				// c'est la 1ere fois, on initialise la liste
				p.addClubs(p.getClub1(), p.getClub2(), p.getClub3());
				cleanPersons.add(p);
			} else {
				// Set donc pas de doublons
				personnePresente.addClubs(p.getClub1(), p.getClub2(), p.getClub3());
			}
		}
		return cleanPersons;
	}

	private static Person getFromListe(String nom, String prenom, List<Person> liste) {

		Optional<Person> resultat = liste.stream().filter(p -> p.getNom().equals(nom) && p.getPrenom().equals(prenom)).findFirst();
		if (resultat.isPresent()) {
			return resultat.get();
		} else {
			return null;
		}
	}

	// fonction lambada pour créer une personne
	public static Function<String, Person> mapToPerson = (line) -> {
		String[] p = line.split(";");
		return new Person(p[0], p[1], p[2], p[3], p[4]);
	};
}

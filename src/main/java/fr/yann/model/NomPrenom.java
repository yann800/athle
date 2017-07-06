package fr.yann.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NomPrenom {

	public String	nom;
	public String	prenom;

	public NomPrenom(String str) {
		String pattern = "([\\p{Lu}\\s-]*)\\s([\\p{Alpha}\\s-\\(\\)]*)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(str);
		if (m.find()) {

			nom = m.group(1).replace("'", " ");
			prenom = m.group(2).replace("'", " ");

			// System.out.println(nom + " " + prenom);
		}

	}

}

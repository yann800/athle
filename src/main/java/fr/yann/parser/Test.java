package fr.yann.parser;

public class Test {
	public static void main(String[] args) {
		String prenom = "toto (FRA)";
		if (prenom.contains("(")) {
			int indexParenthese = prenom.indexOf("(");
			String pays = prenom.substring(indexParenthese);
			System.out.println(pays);
			prenom = prenom.substring(0, indexParenthese - 1);
		}
		System.out.println(prenom);
	}
}

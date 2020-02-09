package fr.yann.parser.cross;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.yann.model.enums.SexeEnum;

/**
 * 
 * Parsing du texte simple issu du copier coller de PDF
 * <tr>
 * <td class="datas0">
 * <tr>
 * <td class="datas1">
 */
public class ParserTxt {

	/**
	 * arg : D:\Users\yabd-el-kader\Desktop\athle\bilan\liste_000.html
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public static void main(String[] args) throws FileNotFoundException {

		StringBuffer	sb		= new StringBuffer();

		String			path	= "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\cross\\2014_cross_reg.txt";

		lireFichier(path, sb, SexeEnum.MASCULIN, 2014);

		System.out.println(sb.toString());

	}

	/**
	 * Ex :
	 * 
	 * @param line
	 * @param sb
	 * @param p_annee
	 * @param isLastLine
	 */
	private static void traiteLigne(String line, StringBuffer sb, String cat, SexeEnum sexeEnum, int p_annee) {

		if (line == null) {
			return;
		}

		// String to be scanned to find the pattern.
		String	motAccent	= "[A-Za-z\\u00C0\\u00C1\\u00C2\\u00C3\\u00C4\\u00C5\\u00C6\\u00C7\\u00C8\\u00C9\\u00CA\\u00CB\\u00CC\\u00CD\\u00CE\\u00CF\\u00D0\\u00D1\\u00D2\\u00D3\\u00D4\\u00D5\\u00D6\\u00D8\\u00D9\\u00DA\\u00DB\\u00DC\\u00DD\\u00DF\\u00E0\\u00E1\\u00E2\\u00E3\\u00E4\\u00E5\\u00E6\\u00E7\\u00E8\\u00E9\\u00EA\\u00EB\\u00EC\\u00ED\\u00EE\\u00EF\\u00F0\\u00F1\\u00F2\\u00F3\\u00F4\\u00F5\\u00F6\\u00F9\\u00FA\\u00FB\\u00FC\\u00FD\\u00FF\\u0153]+";

		String	rang		= "(\\d+)\\s";
		String	chrono		= "(\\d{1,2}'\\d\\d\"\\d{1,2})\\s";
		String	nom			= "([A-Z]+)\\s";
		String	prenom		= "(" + motAccent + ")\\s";
		String	club		= "([a-zA-Z\\s\\.]+[a-zA-Z])\\s";
		String	annee		= "(\\d{2})\\s";
		String	strDate		= "(\\d{2}/\\d{2}/\\d{4})\\s";
		String	ville		= "(" + motAccent + ")";

		// String pattern =
		// (\d)+\s(1'\d\d"\d\d)\s([A-Z]+)\s([^\s]+)\s([A-Za-z\s]+)\s(\d\d)\s(\d{2}/\d\d/\d\d\d\d)\s(\w+)
		String	pattern		= rang + chrono + nom + club + annee + strDate + ville;

		// Create a Pattern object
		Pattern	r			= Pattern.compile(pattern);

		// Now create matcher object.
		Matcher	m			= r.matcher(line);
		if (m.find()) {

			// for (int i = 0; i < 9; i++) {
			// System.out.println(i + " " + m.group(i));
			// }

			prenom = m.group(4).replace("'", " ");
			String pays = "";

			if (prenom.contains("(")) {
				int indexParenthese = prenom.indexOf("(");
				pays = prenom.substring(indexParenthese);
				System.out.println(pays);
				prenom = prenom.substring(0, indexParenthese - 1);
			}

			String		_chrono			= null;
			String		_dep			= null;
			int			_naissance		= 0;
			int			_sexe			= 0;
			String		_championnat	= null;
			int			_rang			= 0;
			String		_nom			= null;
			String		_club			= null;
			String		_cat			= null;
			int			_annee			= 0;
			/*
			 * Integer.parseInt(m.group(1)), // rang m.group(2).replace("\"",
			 * ".").replace("'", "."), // chrono m.group(3).replace("'", " "), // nom
			 * m.group(5).replace("'", " "), // club "", // ligue
			 * Integer.parseInt(m.group(6)), // anneeNaissance m.group(7), // datePerf
			 * m.group(8).replace("'", " "), // ville cat, sexeEnum.getCodeInt(), pays,
			 * p_annee);
			 */
			LigneCross	lc				= new LigneCross(_rang, _chrono, _nom, _club, _dep, _cat, _naissance, _sexe, _championnat, _annee);

			sb.append(lc.toString() + "\n");
		}

	}

	private static void lireFichier(String path, StringBuffer sb, SexeEnum sexeEnum, int annee) throws FileNotFoundException {
		File			f	= new File(path);
		FileReader		fr	= new FileReader(f);
		BufferedReader	br	= new BufferedReader(fr);

		try {
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				traiteLigne(line, sb, "sen", sexeEnum, annee);
			}

			br.close();
			fr.close();
		} catch (IOException exception) {
			System.err.println("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

}

package fr.yann.parser.record_wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetNomPays {

	public static void main(String[] args) throws Exception {
		Map<Integer, String> map = getMap();
		for (Integer num : map.keySet()) {
			System.out.println("INSERT INTO pays(id, nom) VALUES (" + num + ", '" + map.get(num) + "');");
		}
	}

	public static Map<Integer, String> getMap() throws Exception {

		Map<Integer, String> map = new HashMap<>();

		List<String> fichiers = new ArrayList<>();

		// parcours("C://workspace_athle//parser//src//main//java//fr//yann//parser//record_wiki//wiki");

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("C://workspace_athle//parser//src//main//java//fr//yann//parser//record_wiki//wiki"))) {
			for (Path path : directoryStream) {
				fichiers.add(path.getFileName().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String f : fichiers) {
			if (f.equals(".gitignore")) {
				continue;
			}
			// System.out.println(f);
			String nom = traite("C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_wiki\\wiki\\" + f);
			int numero = Integer.parseInt(f.replace("pays(", "").replace(")", ""));
			map.put(numero, clean(nom));
			// System.out.println(numero + " " + nom);
		}

		return map;
	}

	private static String clean(String nom) {
		return nom
				.replace("Records de ", "")
				.replace("Records des ", "")
				.replace("Records du ", "")
				.replace("Records d’", "")
				.replace("Records d'", "")
				.replace("d'athlétisme", "").trim();

	}

	//	private static void parcours(String repertoire) {
	//		File rep = new File(repertoire);
	//		File[] fichiersJava = rep.listFiles(new FilenameFilter() {
	//			public boolean accept(File dir, String name) {
	//				return true;
	//			}
	//		});
	//
	//		for (File f : fichiersJava) {
	//			System.out.println(f.getAbsolutePath());
	//			f.renameTo(new File(f.getAbsolutePath().replace(" ", "")));
	//		}
	//	}

	private static String traite(final String pathFile) throws Exception {

		String nom = null;

		File f = new File(pathFile);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line;

		while ((line = br.readLine()) != null) {

			if (!line.startsWith("<h1")) {
				continue;
			}

			nom = getNom(line);
			break;

		}
		br.close();
		fr.close();
		return nom;
	}

	private static String getNom(String str) {
		// supression des balises html
		String patternCleanHtml = "<[^>]*>";
		Pattern p1 = Pattern.compile(patternCleanHtml);
		Matcher m1 = p1.matcher(str);
		String strPlainText = m1.replaceAll("");

		return strPlainText;
	}
}

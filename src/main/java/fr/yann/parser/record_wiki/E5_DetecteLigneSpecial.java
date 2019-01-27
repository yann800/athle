package fr.yann.parser.record_wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

import fr.yann.parser.record_wiki.service.GetNomPaysWikiEn;

/**
 * 
 * HTML -> sysout.print
 *
 */
public class E5_DetecteLigneSpecial {

	public  static final String PATH_PAGES_WIKI = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_wiki\\pages";

	// Insert.main(path + ".sql");
	public static void main(String[] args) throws Exception {

		Map<Integer, String> map = GetNomPaysWikiEn.getMap();

		for (Integer num : map.keySet()) {
			
			// if (num != 69) {continue;}
			
			System.out.println("===" + num + " " + map.get(num));

			String path = PATH_PAGES_WIKI + "\\pays" + num + ".html";

			detecte(path, num);

		}


	}

	private static void detecte(final String pathFile, int idPays) throws Exception {

		File f = new File(pathFile);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line;

		int i = 0;
		
		while ((line = br.readLine()) != null) {

			if (line.contains(" X")){
				System.out.println(line);
				i = 3;
			}
			else if (i > 0){
				System.out.println(line);
				i--;
			}
			

		}
		br.close();
		fr.close();
	}
}

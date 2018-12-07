package fr.yann.parser.record_wiki;

import java.util.Map;

import fr.yann.dao.Insert;
import fr.yann.parser.record_wiki.service.GetNomPaysWikiEn;

/**
 * 
 * SQL INSERT
 *
 */
public class E2_InsertRowFromSqlFiles {
	public static void main(String[] args) {

		Map<Integer, String> map = GetNomPaysWikiEn.getMap();

		for (Integer num : map.keySet()) {
			
			// if (num == 69) {continue;}
			
			String path = "D:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\record_wiki\\wiki\\pays" + num + ".html";

			Insert.main(path.replace(".html", ".sql"));
		}


	}
}

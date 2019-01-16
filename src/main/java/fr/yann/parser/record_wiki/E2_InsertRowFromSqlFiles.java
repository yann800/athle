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
			
			String path = E1_TraiteFichierRecordsWiki.PATH_PAGES_WIKI + "\\pays" + num + ".sql";

			Insert.main(path);
			// System.out.println("insert into pays_wiki(id, nom) values(" + num + ",'" + map.get(num)+ "');");
		
		}


	}
}

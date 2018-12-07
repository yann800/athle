package fr.yann.parser.record_wiki.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class SqlService {

	public static void writeFile(String path, String str) throws IOException {

		String pathSql = path.replace(".html", ".sql");

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathSql), "utf-8"))) {
			writer.write(str);
		} catch (IOException ex) {
			throw ex;
		}

	}
}

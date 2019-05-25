package fr.yann.parser.interclub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import fr.yann.parser.interclub.csv.IC;

public class InterclubJsonToSql {

	private static final String pathFolder = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\interclub\\json\\";

	public static void main(String[] args) {

		traiteJson();
		// System.out.println(l.toSql());
	}

	private static void traiteJson() {
		// File f = new File(pathFolder + "interclub.json");

		/// charger Json
		JsonParser jsonParser = new JsonParser();
		// Reader in = new Input;
		JsonReader jsonReader = new JsonReader(in);
		// JsonObject json = jsonParser.parse(jsonReader);
	}
}

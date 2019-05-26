package fr.yann.parser.interclub;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.yann.model.json.interclub.ICJson;
import fr.yann.model.json.interclub.PerfJson;

public class InterclubJsonToSql {

	private static final String pathFolder = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\interclub\\json\\";

	private static List<ICJson> json;
	
	public static void main(String[] args) throws IOException {

		// 1. JSON to Java object, read it from a file.
		// Cross cross = gson.fromJson(new FileReader("cross.json"), Cross.class);
		Type listType = new TypeToken<List<ICJson>>() {}.getType();
		json = new Gson().fromJson(new FileReader(pathFolder + "acpj.json"), listType);

		read(json.get(0));

	}

	private static void read(ICJson json) {

		for (PerfJson perf : json.getPerfs()) {
			System.out.println(perf);
		}
	}

	
}

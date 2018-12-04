package fr.yann.service;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Comparaison {


	private static List<Cross> listCross;

	public static void main(String[] args) throws IOException {

		// 1. JSON to Java object, read it from a file.
		// Cross cross = gson.fromJson(new FileReader("cross.json"), Cross.class);
		Type listType = new TypeToken<List<Cross>>() {}.getType();
		listCross = new Gson().fromJson(new FileReader("D:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\service\\cross.json"), listType);

		String k1 = "Dep14";
		String k2 = "Dep15";
		String k3 = "Reg14";
		String k4 = "Reg15";
		
		generateLinks(getCross(k1), getCross(k2));
		generateLinks(getCross(k2), getCross(k3));
		generateLinks(getCross(k3), getCross(k4));

	}

	private static Cross getCross(String k) {
		for (Cross c : listCross) {
			if (c.getKey().equals(k)) {
				return c;
			}
		}
		return null;
	}

	private static void generateLinks(Cross c1, Cross c2) {

		for (Field a : c1.getFields()) {
			if (contains(c2.getFields(), a)) {
				System.out.println("{ from: \"" + c1.getKey() + "\", fromPort: \"" + a.getName() + "\", to: \"" + c2.getKey() + "\", toPort: \"" + a.getName() + "\" },");
			}
		}
	}

	private static boolean contains(List<Field> liste, Field a) {
		for (Field f : liste) {
			if (f.getName().equals(a.getName())) {
				return true;
			}
		}
		return false;
	}
}
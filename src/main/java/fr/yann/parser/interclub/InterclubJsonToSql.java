package fr.yann.parser.interclub;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;
import fr.yann.model.json.interclub.ICJson;
import fr.yann.model.json.interclub.PerfJson;
import fr.yann.service.CotationService;

public class InterclubJsonToSql {

	private static final String pathFolder = "C:\\workspace_athle\\parser\\src\\main\\java\\fr\\yann\\parser\\interclub\\json\\";

	private static List<ICJson> json;
	
	
	public static void main(String[] args) throws NumberFormatException, Exception {

		// 1. JSON to Java object, read it from a file.
		// Cross cross = gson.fromJson(new FileReader("cross.json"), Cross.class);
		Type listType = new TypeToken<List<ICJson>>() {}.getType();
		json = new Gson().fromJson(new FileReader(pathFolder + "acpj.json"), listType);

		corrigePoints(json.get(0));
		
		// read(json.get(0));

	}

	private static void displayAllJson(ICJson json) {

		for (PerfJson perf : json.getPerfs()) {
			System.out.println(perf);
		}
	}
	
	private static void corrigePoints(ICJson json) throws NumberFormatException, Exception {

		CotationService.init(EpreuveEnum.COURSE_800, SexeEnum.MASCULIN);
		
		for (PerfJson perf : json.getPerfs()) {
			if (perf.getPt().equals("0")){
				setCotation(perf);
			}
		}
	}

	private static void setCotation(PerfJson perf) throws NumberFormatException, Exception {
		if (perf.getE().equals("800") && perf.getS().equals("M")){
			perf.setPt(CotationService.getPoints(perf.getP()));
			System.out.println("CORRECTION " + perf);
		}
	}

	
}

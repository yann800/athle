package fr.yann.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class CotationServiceCourse {
	
	private static final String		pathFolderCsv	= "C:\\temp\\csv\\";

	private static List<PerfPoints> listePerfPoints = null;
	
	// EpreuveEnum epreuve, SexeEnum sexe en amont dans init()
	private static int getPointsWithInterval(double chrono) {
		
		for (int tentative = 0; tentative < 10; tentative++) {
			double add = Double.parseDouble("0.0" + tentative);
			int points = getPoints(chrono + add);
			if (points > 0) {
				return points;
			}
		}
		for (int tentative = 10; tentative < 100; tentative++) {
			double add = Double.parseDouble("0." + tentative);
			int points = getPoints(chrono + add);
			if (points > 0) {
				return points;
			}
		}

		System.err.println(chrono + " non trouvé");
		return 0;
	}

	private static int getPoints(double chrono) {
		// System.err.println("getPoints " + chrono);
		for (PerfPoints pp:listePerfPoints){
			if(pp == null){
				continue;
			}
			double current = Double.parseDouble(pp.perf);
			// System.out.println("test " + current + " " + chrono);
			if (current == chrono){
				return pp.points;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {

		init(EpreuveEnum.COURSE_200, SexeEnum.FEMININ);
		
		double chronoSecondes = Utilities.getChronoSecondesFromPerf("25.92");
		
		//		double chrono =Double.parseDouble("100.00");
		//		double tenth  = Double.parseDouble("0.04");
		//		System.out.println(tenth);
		System.out.println(">>>>>> " + getPointsWithInterval(chronoSecondes));
	}
	

	// perf, points
	public static void init(EpreuveEnum e, SexeEnum s) {

		String fileName = getFileName(e, s);
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathFolderCsv + fileName))) {

			List<PerfPoints> perfPoints = br.lines().map(mapToPerfPoints)
					// .limit(100)
					.collect(Collectors.toList());
			listePerfPoints = perfPoints;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// "Table Outdoor 2017 - MALE - 800m.csv"
	private static String getFileName(EpreuveEnum e, SexeEnum s) {
		String fileName = "Table Outdoor 2017 - ";
		if (s == SexeEnum.FEMININ){
			fileName = fileName + "FEMALE - ";
		}
		else {
			fileName = fileName + "MALE - ";
		}
		
		if (e == EpreuveEnum.COURSE_100_HAIES){return fileName + "100mH.csv";}
		if (e == EpreuveEnum.COURSE_110_HAIES){return fileName + "110mH.csv";}
		if (e == EpreuveEnum.COURSE_400_HAIES) {return fileName + "400mH.csv";}
		if (e == EpreuveEnum.MARCHE_3000){return fileName + "3km W.csv";}
		if (e == EpreuveEnum.MARCHE_5000){return fileName + "5km W.csv";}

		if (e == EpreuveEnum.COURSE_3000_STEEPLE){return fileName + "3000m SC.csv";}
		
		// tout les cas courses simples
		return fileName + e.code + "m.csv";
	}

	// fonction lambada pour créer une personne
	public static Function<String, PerfPoints> mapToPerfPoints = (line) -> {
		if (line.startsWith("p")){
			return null;
		}
		String[] p = line.split(",");
		return new PerfPoints(p[0], Integer.parseInt(p[1]));
	};

	public static int getPoints(String perf) throws NumberFormatException, Exception {
		String clean = clean(perf);
		return getPointsWithInterval(Utilities.parseTimeDot(clean));
	}

	// 25.92 (+0.7)
	private static String clean(String perf) {
		if (!perf.contains("(")){
			return perf;
		}
		int index = perf.indexOf("(");
		
		return perf.substring(0, index).trim();
	}
}

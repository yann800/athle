package fr.yann.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.yann.model.enums.EpreuveEnum;
import fr.yann.model.enums.SexeEnum;

public class CotationServiceSautLancer {
	
	private static final String		pathFolderCsv	= "C:\\temp\\csv\\";

	private static List<PerfPoints> listePerfPoints = null;
	
	// EpreuveEnum epreuve, SexeEnum sexe en amont dans init()
	private static int getPointsWithInterval(double perf) {

		for (int tentative = 0; tentative < 10; tentative++) {
			double add = Double.parseDouble("0.0" + tentative);
			int points = getPoints(perf + add);
			if (points > 0) {
				return points;
			}
		}
		System.err.println(perf + " non trouvé");
		return 0;
	}

	private static int getPoints(double perf) {
		// System.err.println("getPoints " + perf);
		for (PerfPoints pp:listePerfPoints){
			if(pp == null){
				continue;
			}
			double current = Double.parseDouble(pp.perf);
			// System.out.println("test " + current + " " + chrono);
			if (current == perf){
				return pp.points;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {

		init(EpreuveEnum.SAUT_TRIPLE, SexeEnum.MASCULIN);
		
		double chronoSecondes = Utilities.getChronoSecondesFromPerf("16.92");
		
		System.out.println(">>>>>> " + getPointsWithInterval(chronoSecondes));
	}
	

	// perf, points
	public static void init(EpreuveEnum e, SexeEnum s) {

		String fileName = getFileName(e, s);
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathFolderCsv + fileName))) {

			List<PerfPoints> perfPoints = br.lines().map(mapToPerfPoints)
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
		
		if (e == EpreuveEnum.LANCER_DISQUE){return fileName + "DT.csv";}
		if (e == EpreuveEnum.LANCER_JAVELOT){return fileName + "JT.csv";}
		if (e == EpreuveEnum.LANCER_MARTEAU){return fileName + "HT.csv";}
		if (e == EpreuveEnum.LANCER_POIDS){return fileName + "SP.csv";}

		if (e == EpreuveEnum.SAUT_HAUTEUR){return fileName + "HJ.csv";}
		if (e == EpreuveEnum.SAUT_LONGUEUR){return fileName + "LJ.csv";}
		if (e == EpreuveEnum.SAUT_TRIPLE){return fileName + "TJ.csv";}
		if (e == EpreuveEnum.SAUT_PERCHE){return fileName + "PV.csv";}
		
		return null;
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
		return getPointsWithInterval(Utilities.parsePerfSautLancer(clean));
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

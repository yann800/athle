package fr.yann.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum EpreuveEnum {
	COURSE_100("100"),
	COURSE_200("200"),
	COURSE_400("400"),
	COURSE_800("800"),
	COURSE_1000("1000"),
	COURSE_1500("1500"),
	COURSE_3000("3000"),
	COURSE_5000("5000"),
	COURSE_10000("10000"),
	COURSE_MARATHON("Marathon"),
	COURSE_100_HAIES("100H"),
	COURSE_110_HAIES("110H"),
	COURSE_400_HAIES("400H"),
	COURSE_3000_STEEPLE("3000 steeple"),	
	
	MARCHE_3000("3000 marche"),
	MARCHE_5000("5000 marche"),

	LANCER_POIDS("Poids"),
	LANCER_DISQUE("Disque"),
	LANCER_MARTEAU("Marteau"),
	LANCER_JAVELOT("Javelot"),
	
	SAUT_LONGUEUR("Longueur"),
	SAUT_TRIPLE("Triple"),
	SAUT_HAUTEUR("Hauteur"),
	SAUT_PERCHE("Perche"),
	
	HEPTATHLON("Heptathlon"),
	DECATHLON("Decathlon"),
	
	RELAIS_4x100("4x100"),
	RELAIS_4x400("4x400")
	;

	private static final Map<String, EpreuveEnum> lookup = new HashMap<String, EpreuveEnum>();

	static {
		for (EpreuveEnum d : EpreuveEnum.values()) {
			lookup.put(d.getCode(), d);
		}
	}

	private EpreuveEnum(String code) {
		this.code = code;
	}
	public final String code;
	public String getCode() {
		return this.code;
	}

	public static EpreuveEnum getEnumFromCode(String str) {
		// ============
		// HAIES
		if (str.contains("aies")) {
			if (str.contains("100")) {
				return EpreuveEnum.COURSE_100_HAIES;
			}
			return EpreuveEnum.COURSE_400_HAIES;
		}
		if (str.startsWith("3000 St") || str.contains("eeple")) {
			return EpreuveEnum.COURSE_3000_STEEPLE;
		}
		// ============
		// MARCHE
		if (str.contains("arche")) {
			if (str.contains("3000")) {return MARCHE_3000;}
			if (str.contains("5000")) {return MARCHE_5000;}
		}
		
		// ============
		// PLAT
		if (str.startsWith("100")) {return COURSE_100;}
		if (str.startsWith("200")) {return COURSE_200;}
		if (str.startsWith("400")) {return COURSE_400;}
		if (str.startsWith("800")) {return COURSE_800;}
		if (str.startsWith("1500")) {return COURSE_1500;}
		if (str.startsWith("3000")) {return COURSE_3000;}
		if (str.startsWith("5000")) {return COURSE_5000;}
		
		
		// ============
		// SAUT
		if (str.startsWith("Longueur")) {
			return EpreuveEnum.SAUT_LONGUEUR;
		}
		if (str.startsWith("Perche")) {
			return EpreuveEnum.SAUT_PERCHE;
		}
		if (str.startsWith("Hauteur")) {
			return EpreuveEnum.SAUT_HAUTEUR;
		}
		if (str.startsWith("Triple")) {
			return EpreuveEnum.SAUT_TRIPLE;
		}
		if (str.startsWith("Poids")) {
			return EpreuveEnum.LANCER_POIDS;
		}
		if (str.startsWith("Disque")) {
			return EpreuveEnum.LANCER_DISQUE;
		}
		if (str.startsWith("Marteau")) {
			return EpreuveEnum.LANCER_MARTEAU;
		}	
		if (str.startsWith("Javelot")) {
			return EpreuveEnum.LANCER_JAVELOT;	
		}
		return lookup.get(str);
	}
}
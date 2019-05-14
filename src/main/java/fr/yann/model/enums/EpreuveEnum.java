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

	LANCER_POIDS("Poids"),
	LANCER_DISQUE("Disque"),
	LANCER_MARTEAU("Marteau"),
	LANCER_JAVELOT("Javelot"),
	
	SAUT_LONGUEUR("Longueur"),
	SAUT_TRIPLE("Triple"),
	SAUT_HAUTEUR("Hauteur"),
	SAUT_PERCHE("Perche"),
	
	HEPTATHLON("Heptathlon"),
	DECATHLON("Decathlon")
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
		if (str.startsWith("100haies")) {
			return EpreuveEnum.COURSE_100_HAIES;
		}
		if (str.startsWith("3000 St")) {
			return EpreuveEnum.COURSE_3000_STEEPLE;
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
package fr.yann.model.enums;

public enum SexeEnum {
	FEMININ, MASCULIN;

	public String getCodeStr() {
		if (this == FEMININ) {
			return "F";
		}
		return "M";
	}

	public int getCodeInt() {
		if (this == FEMININ) {
			return 1;
		}
		return 0;
	}

	public static SexeEnum getEnumFromCode(String code) {
		if (code.equals("F")) {
			return SexeEnum.FEMININ;
		}
		if (code.equals("M")) {
			return SexeEnum.MASCULIN;
		}
		return null;
	}

}

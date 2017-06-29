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

}

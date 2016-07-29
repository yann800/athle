package fr.yann.model;

public enum EpreuveEnum {
	COURSE_800, COURSE_1500, COURSE_3000, COURSE_10_KM;

	public int getCode() {
		if (this == COURSE_800) {
			return 800;
		}
		if (this == COURSE_1500) {
			return 1500;
		}

		if (this == COURSE_3000) {
			return 3000;
		}

		if (this == COURSE_10_KM) {
			return 10;
		}
		return 0;
	}

}

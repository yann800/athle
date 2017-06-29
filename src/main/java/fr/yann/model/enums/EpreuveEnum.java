package fr.yann.model.enums;

public enum EpreuveEnum {
	COURSE_400("400"),
	COURSE_400_HAIES("400H"),
	COURSE_800("800"),
	COURSE_1500("1500"),
	COURSE_3000("3000"),
	COURSE_10_KM("10")
	;

	private EpreuveEnum(String code) {
		this.code = code;
	}
	public final String code;
	public String getCode() {
		return this.code;
	}
}

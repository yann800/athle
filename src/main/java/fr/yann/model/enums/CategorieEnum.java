package fr.yann.model.enums;

public enum CategorieEnum {
	CA, JU, ES, SE, VE;

	public static CategorieEnum getFromCode(String val) {

		if (val.equals("CAF") || val.equals("CAM")) {
			return CA;
		}
		if (val.equals("JUF") || val.equals("JUM")) {
			return JU;
		}
		if (val.equals("ESF") || val.equals("ESM")) {
			return ES;
		}
		if (val.equals("SEF") || val.equals("SEM")) {
			return SE;
		}
		if (val.equals("VEF") || val.equals("VEM")) {
			return VE;
		}
		return null;
	}
}

package fr.yann.model.json.interclub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerfJson {

	@SerializedName("n")
	@Expose
	private String	n;

	@SerializedName("c")
	@Expose
	private String	c;

	
	@SerializedName("s")
	@Expose
	private String	s;
	
	
	@SerializedName("e")
	@Expose
	private String e;
	
	@SerializedName("p")
	@Expose
	private String p;
	
	@SerializedName("pt")
	@Expose
	private String pt;

	@SerializedName("niv")
	@Expose
	private String niv;

	@SerializedName("nai")
	@Expose
	private String nai;
	
	@SerializedName("a")
	@Expose
	private int a;
	
	@SerializedName("d")
	@Expose
	private String d;
	
	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "PerfJson [n=" + n + ", c=" + c + ", s=" + s + ", e=" + e + ", p=" + p + ", pt=" + pt + ", niv=" + niv
				+ ", nai=" + nai + ", a=" + a + ", d=" + d + "]";
	}
}
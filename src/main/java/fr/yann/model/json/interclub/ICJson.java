package fr.yann.model.json.interclub;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ICJson {

	@SerializedName("key")
	@Expose
	private String		key;
	@SerializedName("fields")
	@Expose
	private List<PerfJson>	perfs	= null;

	public String getKey() {
		return key;
}

	public void setKey(String key) {
		this.key = key;
	}

	public List<PerfJson> getPerfs() {
		return perfs;
	}

	public void setPerfs(List<PerfJson> perfs) {
		this.perfs = perfs;
	}
}
package fr.yann.model.json.cross;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cross {

	@SerializedName("key")
	@Expose
	private String		key;
	@SerializedName("fields")
	@Expose
	private List<Field>	fields	= null;

	public String getKey() {
		return key;
}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
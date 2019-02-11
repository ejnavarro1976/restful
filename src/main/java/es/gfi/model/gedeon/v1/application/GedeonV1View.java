package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1View implements Serializable {

	private static final long serialVersionUID = 4485058890567187325L;

	private String def;
	private String path;

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1Root implements Serializable {

	private static final long serialVersionUID = 4653725329102057998L;

	private String displayPath;
	private String path;
	private String template;

	public String getDisplayPath() {
		return displayPath;
	}

	public void setDisplayPath(String displayPath) {
		this.displayPath = displayPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}

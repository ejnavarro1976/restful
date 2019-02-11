package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1PropertyDefinition implements Serializable {

	private static final long serialVersionUID = 8999378251669339246L;

	private String datatype;
	private String name;
	private String title;

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

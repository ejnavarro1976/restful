package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1TypeDefinition implements Serializable {

	private static final long serialVersionUID = 6339336814456625460L;

	private Boolean aspect;
	private String name;
	private GedeonV1PropertyDefinition properties;
	private String title;

	public Boolean getAspect() {
		return aspect;
	}

	public void setAspect(Boolean aspect) {
		this.aspect = aspect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GedeonV1PropertyDefinition getProperties() {
		return properties;
	}

	public void setProperties(GedeonV1PropertyDefinition properties) {
		this.properties = properties;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

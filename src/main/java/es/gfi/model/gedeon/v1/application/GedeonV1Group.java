package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1Group implements Serializable {

	private static final long serialVersionUID = 2881031074805124534L;

	private String displayName;
	private String name;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}

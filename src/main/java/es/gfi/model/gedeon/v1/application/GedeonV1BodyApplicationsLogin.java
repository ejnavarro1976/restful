package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1BodyApplicationsLogin implements Serializable {

	private static final long serialVersionUID = -1010898413514408934L;

	private String name;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

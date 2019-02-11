package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1TypeMap implements Serializable {

	private static final long serialVersionUID = 2283439054673714486L;

	private String document;
	private String folder;

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}

package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1Model implements Serializable {

	private static final long serialVersionUID = 1787165143695995982L;

	private GedeonV1TypeDefinition definitions;
	private String file;
	private String name;
	private GedeonV1TypeMap typeMap;

	public GedeonV1TypeDefinition getDefinitions() {
		return definitions;
	}

	public void setDefinitions(GedeonV1TypeDefinition definitions) {
		this.definitions = definitions;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GedeonV1TypeMap getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(GedeonV1TypeMap typeMap) {
		this.typeMap = typeMap;
	}

}

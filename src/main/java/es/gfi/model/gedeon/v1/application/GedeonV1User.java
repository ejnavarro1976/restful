package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;
import java.util.List;

public class GedeonV1User implements Serializable {

	private static final long serialVersionUID = -602631686411840940L;

	private String email;
	private List<String> groups;
	private String username;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

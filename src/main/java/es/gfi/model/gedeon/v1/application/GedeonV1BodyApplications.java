package es.gfi.model.gedeon.v1.application;

import java.io.Serializable;

public class GedeonV1BodyApplications implements Serializable {

	private static final long serialVersionUID = -1010898413514408934L;

	private String description;
	private GedeonV1Group groups;
	private String id;
	private GedeonV1Model model;
	private String name;
	private GedeonV1Root root;
	private GedeonV1User users;
	private GedeonV1View views;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GedeonV1Group getGroups() {
		return groups;
	}

	public void setGroups(GedeonV1Group groups) {
		this.groups = groups;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GedeonV1Model getModel() {
		return model;
	}

	public void setModel(GedeonV1Model model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GedeonV1Root getRoot() {
		return root;
	}

	public void setRoot(GedeonV1Root root) {
		this.root = root;
	}

	public GedeonV1User getUsers() {
		return users;
	}

	public void setUsers(GedeonV1User users) {
		this.users = users;
	}

	public GedeonV1View getViews() {
		return views;
	}

	public void setViews(GedeonV1View views) {
		this.views = views;
	}

}
